package team.yqby.platform.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.condition.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import team.yqby.platform.common.util.ExcelUtil;
import team.yqby.platform.common.util.RegexUtil;
import team.yqby.platform.config.PublicConfig;
import team.yqby.platform.dto.model.BillResDto;
import team.yqby.platform.dto.model.OrderResDto;
import team.yqby.platform.manager.PrepaidCardPayManager;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@Service
@Slf4j
public class PrepaidCardPayService {
    private String imageFilePath = PublicConfig.IMAGE_PATH;

    @Autowired
    private PrepaidCardPayManager prepaidCardPayManager;

    /**
     * 获取Cookie信息
     */
    public void getUrlCookie() {
        prepaidCardPayManager.saveCookie();
    }

    /**
     * 批量操作入口
     *
     * @param excelFilePath
     */
    public void batchDo(String excelFilePath, WebSocketSession session) throws IOException {
        Date startDate = new Date();
        int payAccountIndex = 0;
        int payTotalCount = 0;
        String[][] barCodeList = ExcelUtil.readExcel(excelFilePath, 0);
        for (int i = 0; i < barCodeList.length; i++) {
            //格式不满足继续下一条记录
            if (!RegexUtil.doRegex(barCodeList[i][0], RegexUtil.number_8_regex)) continue;
            String barCodeNo = barCodeList[i][0];

            //1.获取cookie
            getUrlCookie();

            //2.查询账单
            BillResDto billResDto = queryBill(barCodeNo);
            payTotalCount++;
            log.info("读取第{}条记录查询账单，条码:{},账单信息:{}", i + 1, barCodeNo, billResDto);
            if (StringUtils.isEmpty(billResDto.getStatus())) {
                session.sendMessage(new TextMessage("条码" + barCodeNo + "无未支付的账单或已支付成功过"));
                log.info("条码{} 无未支付的账单或已支付成功过", barCodeNo);
                continue;
            }
            //3.下单支付
            createOrderAndPay(billResDto, excelFilePath, barCodeNo, payAccountIndex, session);
        }
        Date endDate = new Date();
        long interval = (startDate.getTime() - endDate.getTime()) / 1000;
        session.sendMessage(new TextMessage(Joiner.on("&").join("通知", "已完成" + payTotalCount + "笔账单充值,花费时间" + interval + "秒")));
    }


    /**
     * 查询账单信息
     *
     * @param barCodeNo 条码
     * @return
     */
    public BillResDto queryBill(String barCodeNo) {
        boolean checkFlag = false;
        String verifyCode = "";
        int checkNum = 3;

        //获取图片验证码识别并校验
        while (!checkFlag && checkNum > 0) {
            verifyCode = prepaidCardPayManager.createImageReadCode(this.imageFilePath);
            checkFlag = StringUtils.equals("0", (String) JSON.parseObject(prepaidCardPayManager.validateCode(verifyCode)).get("code"));
            checkNum--;
        }

        //未校验通过直接结束
        if (!checkFlag) {
            log.error("图片验证码多次校验未通过,条码:{}", barCodeNo);
            return null;
        }
        //查询缴费账单
        return prepaidCardPayManager.queryBill(verifyCode, barCodeNo);
    }

    /**
     * 下单并支付
     *
     * @param billResDto      账单信息
     * @param excelFilePath   excel文件地址
     * @param barCodeNo       条码
     * @param payAccountIndex 支付账号索引
     */
    public void createOrderAndPay(BillResDto billResDto, String excelFilePath, String barCodeNo, int payAccountIndex, WebSocketSession session) throws IOException {
        try {

            String[][] payAccountList = ExcelUtil.readExcel(excelFilePath, 1);
            String payAccount = "";
            String payPwd = "";
            BigDecimal accountBalance = null;
            boolean payCompleteFlag = false;

            while (!payCompleteFlag) {
                payAccount = payAccountList[payAccountIndex][0];
                payPwd = payAccountList[payAccountIndex][1];
                //1.账号不满足格式
                if (!RegexUtil.doRegex(payAccount, RegexUtil.number_12_regex)) {
                    payAccountIndex++;
                    if (payAccountIndex >= payAccountList.length) {
                        log.info("账号:{}无新的支付方式可支付", payAccount);
                        return;
                    }
                    //获取账号，密码
                    payAccount = payAccountList[payAccountIndex][0];
                    payPwd = payAccountList[payAccountIndex][1];
                }
                Thread.sleep(1000 * 3 * 1);
                accountBalance = prepaidCardPayManager.queryBalance(payAccount);
                log.info("查询11888账户[{}]的余额:{}", payAccount, accountBalance);
                //余额不足使用新的账号
                if (accountBalance.longValue() < billResDto.getAmount().longValue()) {
                    payAccountIndex++;
                    log.error("账号[{}]的账单金额:{},余额:{},余额不足以继续支付,条码:{}", payAccount, billResDto.getAmount(), accountBalance, barCodeNo);
                    continue;
                }
                Thread.sleep(1000 * 5 * 1);
                //下单
                OrderResDto orderResDto = prepaidCardPayManager.createOrder(billResDto.getBarcode(), billResDto.getMqbilldate(), String.valueOf(billResDto.getAmount()), barCodeNo);
                Thread.sleep(1000 * 2 * 2);
                String payStatus = "充值失败";
                //支付
                String payResult = prepaidCardPayManager.billPay(payPwd, orderResDto.getMsgSeq(), orderResDto.getOrderIDType(), orderResDto.getOrderID(), orderResDto.getOrderType(), orderResDto.getOrderFrom(), orderResDto.getAmount_1(), orderResDto.getCreateDateTime(), orderResDto.getStatus(), orderResDto.getMerchantID(), orderResDto.getPrint(), String.valueOf(billResDto.getAmount()), billResDto.getBarcode(), orderResDto.getBilldate(), orderResDto.getDeviceNo(), payPwd);

                if (payResult.contains("支付成功")) {
                    payStatus = "充值成功";
                    paySuccessCount++;
                    accountBalance = accountBalance.subtract(billResDto.getAmount());
                }
                payCompleteFlag = true;
                session.sendMessage(new TextMessage(StringUtils.join(new String[]{orderResDto.getMsgSeq(), barCodeNo, billResDto.getBarcode(), billResDto.getMqbilldate(), String.valueOf(billResDto.getAmount()), payAccount, String.valueOf(accountBalance), payStatus}, "_")));
            }

        } catch (InterruptedException ex) {
            log.error("InterruptedException exception,error", ex);
        }
    }
}
