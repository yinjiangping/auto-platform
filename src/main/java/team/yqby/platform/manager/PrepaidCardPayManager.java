package team.yqby.platform.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import team.yqby.platform.common.WebCall;
import team.yqby.platform.common.util.RegexUtil;
import team.yqby.platform.config.ApiUrls;
import team.yqby.platform.config.PublicConfig;
import team.yqby.platform.dto.model.BillResDto;
import team.yqby.platform.dto.model.OrderResDto;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * <p>
 * 预付卡支付服务
 * </p>
 * User：jumping Date： 2016/10/16 0016 ProjectName:settlement Version：1.0
 */
@Service
@Slf4j
public class PrepaidCardPayManager {

    private String homeUrl = PublicConfig.BILL_HOME_URL;

    /**
     * 获取浏览器Cookie信息
     *
     * @return
     */
    public void saveCookie() {
        WebCall.getCookie(this.homeUrl);
    }

    /**
     * 生成验证码并识别
     *
     * @return
     */
    public String createImageReadCode(String filePath) {
        return WebCall.downloadImageReadCode(homeUrl, Joiner.on("").join(ApiUrls.CREATE_CODE_URL, "?", "key=validateName&time=0.76" + System.currentTimeMillis()), filePath);
    }

    /**
     * 验证图片验证码
     *
     * @param code
     */
    public String validateCode(String code) {
        return WebCall.postReqUrl(homeUrl, ApiUrls.VALIDATE_CODE_URL + "?key=validateName&check=" + code, "");
    }

    /**
     * 查询账单
     *
     * @return
     */
    public BillResDto queryBill(String code, String txVal) {
        String billResult = WebCall.postReqUrl(homeUrl, ApiUrls.QUERY_BILL_URL, Joiner.on("&").join("code=" + code, "fxVal=", "phVal=", "radioVal=0", "txVal=" + txVal));
        BillResDto billResDto = JSON.parseObject(billResult).getObject("barCodeMap", BillResDto.class);
        return billResDto;
    }


    /**
     * 创建订单
     *
     * @return
     */
    public OrderResDto createOrder(String barCode, String mqBillDate, String amount, String barCodeNo) throws IOException {
        String orderResult = WebCall.postReqUrl(homeUrl, ApiUrls.CREATE_ORDER_URL, Joiner.on("&").join("billNum=" + barCode, "billDate=" + mqBillDate, "amount=" + amount, "deviceNo=" + barCodeNo));
        return new OrderResDto(getInputValue(orderResult, "msgSeq"), getInputValue(orderResult, "OrderIDType"), getInputValue(orderResult, "OrderID"), getInputValue(orderResult, "OrderType"),
                getInputValue(orderResult, "OrderFrom"), getInputValue(orderResult, "Amount"), getInputValue(orderResult, "CreateDateTime"), getInputValue(orderResult, "Status"), getInputValue(orderResult, "MerchantID"),
                getInputValue(orderResult, "print"), getInputValue(orderResult, "amount"), getInputValue(orderResult, "billdate"), getInputValue(orderResult, "deviceNo"));
    }

    /**
     * 账单支付
     *
     * @return
     */

    public String billPay(String pwd, String msgSeq, String orderIDType, String orderId, String orderType, String orderFrom, String amount, String createDateTime, String status, String merchantId, String print, String amount2, String barCode, String billDate, String deviceNo, String cardPwd) {
        return WebCall.postReqUrl(homeUrl, ApiUrls.ORDER_PAY_URL, Joiner.on("&").join("chargeType=3", "paytype=", "deviceNoBack=", "account=", "paypassword=", "AccountID=", "Pwd=" + pwd,
                "randomflag=", "msgSeq=" + msgSeq, "OrderIDType=" + orderIDType, "OrderID=" + orderId, "OrderType=" + orderType, "OrderFrom=" + orderFrom, "Amount=" + amount,
                "CreateDateTime=" + createDateTime, "Status=" + status, "MerchantID=" + merchantId, "AccountType=", "print=" + print, "encryptType=2", "encryptData=", "signFlag=", "isComCard=",
                "cardType=3", "amount=" + amount2, "bill=" + barCode, "billdate=" + billDate, "BankID=999", "deviceNo=" + deviceNo, "type=6", "ffkno=", "ffkpwd=", "cardoth=", "cards=" + cardPwd));
    }

    /**
     * 查询余额
     *
     * @param cardNo 账号
     * @return
     */
    public BigDecimal queryBalance(String cardNo) {
        try {
            String balanceResult = WebCall.postReqUrl(homeUrl, ApiUrls.QUERY_ACCOUNT_BALANCE + "?cardNum=" + cardNo, "");
            JSONObject jsonObject = JSON.parseObject(balanceResult).getJSONObject("RESULT");
            if ("可用".equals(jsonObject.getString("cardstatus"))) {
                return jsonObject.getBigDecimal("cardvalue");
            }
        } catch (Exception e) {
            log.error("cardNo:{} queryBalance exception,error,", cardNo, e);
        }
        return new BigDecimal("0.00");
    }

    /**
     * 获取INPUT中的值
     *
     * @param restText 下单返回结果
     * @param keyName  INPUT中NAME的名称
     * @return
     */

    private static String getInputValue(String restText, String keyName) {
        String inputStr = RegexUtil.doRegexValue(restText, keyName + "\" value=\"(.*?)\"");
        return inputStr;
    }
}
