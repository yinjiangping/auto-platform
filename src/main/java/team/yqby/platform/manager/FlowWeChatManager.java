package team.yqby.platform.manager;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.yqby.platform.common.WebCall;
import team.yqby.platform.common.emodel.ServiceErrorCode;
import team.yqby.platform.common.enums.ArchiveFlagEnum;
import team.yqby.platform.common.enums.CheckStatusEnum;
import team.yqby.platform.common.enums.TransStatusEnum;
import team.yqby.platform.common.util.*;
import team.yqby.platform.config.PublicConfig;
import team.yqby.platform.dto.model.FlowOrder;
import team.yqby.platform.dto.model.FlowOrderExample;
import team.yqby.platform.dto.model.FlowStock;
import team.yqby.platform.dto.model.FlowStockExample;
import team.yqby.platform.dto.model.inner.WeChatCreateOrder;
import team.yqby.platform.dto.model.res.FlowOrderRes;
import team.yqby.platform.exception.AutoPlatformException;
import team.yqby.platform.mapper.FlowOrderMapper;
import team.yqby.platform.mapper.FlowStockMapper;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 微信服务类
 * </p>
 * User：jumping Date： 2017/1/2 0002 Version：1.0
 */
@Slf4j
@Service
public class FlowWeChatManager {
    @Autowired
    private FlowStockMapper flowStockMapper;
    @Autowired
    private FlowOrderMapper flowOrderMapper;

    /**
     * 检查商品价格
     */
    public FlowStock checkGoodsPrice(String flowID, Long flowCurrentCost) {
        //1.根据流量编号查询价格
        FlowStockExample flowStockExample = new FlowStockExample();
        flowStockExample.createCriteria().andFlowIdEqualTo(flowID).andArchiveFlagEqualTo(ArchiveFlagEnum.STR_0.getCode());
        List<FlowStock> flowStocks = flowStockMapper.selectByExample(flowStockExample);
        if (flowStocks == null || flowStocks.size() == 0) {
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10001);
        }

        //2.判断商户价格是否一致
        FlowStock flowStock = flowStocks.get(0);
        Long currentCost = flowStock.getFlowCurrentCost();
        if (flowCurrentCost.longValue() != currentCost.longValue()) {
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10002);
        }
        return flowStock;
    }

    /**
     * 支付下单
     *
     * @param flowStock 商品信息
     * @param productNo 手机号
     * @param openID    用户编号
     * @return
     */
    public String createPayOrder(FlowStock flowStock, String productNo, String openID) {
        FlowOrder flowOrder = new FlowOrder();
        flowOrder.setOrderId(NumberUtil.getOrderNoRandom());
        flowOrder.setOrderTime(new Date());
        flowOrder.setFlowId(Long.valueOf(flowStock.getFlowId()));
        flowOrder.setOriginalCost(flowStock.getFlowOriginalCost());
        flowOrder.setCurrentCost(flowStock.getFlowCurrentCost());
        flowOrder.setPhone(productNo);
        flowOrder.setOpenId(openID);
        flowOrder.setTransStatus(TransStatusEnum.INI.getStatus());
        flowOrder.setPayReqTime(new Date());
        flowOrder.setCheckStatus(CheckStatusEnum.STR_0.getCode());
        flowOrder.setArchiveFlag(ArchiveFlagEnum.STR_0.getCode());
        flowOrder.setCreateBy(openID);
        flowOrder.setCreateDate(new Date());
        int i = flowOrderMapper.insert(flowOrder);
        if (i == 0) {
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10003);
        }
        return flowOrder.getOrderId();
    }

    /**
     * 微信下单
     *
     * @param openId   用户编号
     * @param orderNo  订单号
     * @param orderAmt 订单金额
     * @return
     * @throws AutoPlatformException
     * @throws Exception
     */
    public WeChatXmlUtil createWeChatOrder(String openId, String orderNo, Long orderAmt) {
        try {
            WeChatCreateOrder weChatCreateOrder = new WeChatCreateOrder();
            weChatCreateOrder.setAppid(PublicConfig.APP_ID);
            weChatCreateOrder.setMch_id(PublicConfig.MCH_ID);
            weChatCreateOrder.setBody(URLEncoder.encode(PublicConfig.GOODS_NAME, PublicConfig.UTF_8));
            weChatCreateOrder.setNonce_str(MD5Util.MD5Encode(Joiner.on("&").join(orderNo, PublicConfig.MCH_KEY)));
            weChatCreateOrder.setNotify_url(PublicConfig.PAY_NOTIFY_URL);
            weChatCreateOrder.setOpenid(openId);
            weChatCreateOrder.setOut_trade_no(orderNo);
            weChatCreateOrder.setSpbill_create_ip(IPUtil.getLocalIP());
            weChatCreateOrder.setTotal_fee(orderAmt);
            weChatCreateOrder.setTrade_type(PublicConfig.TRADE_TYPE);
            weChatCreateOrder.setSign(WeChatXmlUtil.getSign(BeanToMapUtil.convertBean(weChatCreateOrder), PublicConfig.MCH_KEY));
            String requestXml = WeChatXmlUtil.toXml(weChatCreateOrder).replace("__", "_");
            String responseXml = WebCall.xmlSyncSend(PublicConfig.WX_CREATE_ORDER_URL, requestXml);
            WeChatXmlUtil weChatXmlUtil = WeChatXmlUtil.fromXML(responseXml);
            //下单成功
            updateOrderStatus(orderNo, TransStatusEnum.WAIT_PAY.getStatus());
            return weChatXmlUtil;
        } catch (AutoPlatformException e) {
            //下单失败
            updateOrderStatus(orderNo, TransStatusEnum.ORDER_FAIL.getStatus());
            log.error("createWeChatOrder AutoPlatformException error,", e);
            throw new AutoPlatformException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            //下单失败
            updateOrderStatus(orderNo, TransStatusEnum.ORDER_FAIL.getStatus());
            log.error("createWeChatOrder AutoPlatformException error,", e);
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10003.getResCode(), e.getMessage());
        }
    }

    /**
     * 更新交易状态
     *
     * @param orderNo
     * @param transStatus
     */
    public void updateOrderStatus(String orderNo, String transStatus) {
        FlowOrder flowOrder = new FlowOrder();
        flowOrder.setTransStatus(transStatus);
        FlowOrderExample flowOrderExample = new FlowOrderExample();
        flowOrderExample.createCriteria().andOrderIdEqualTo(orderNo).andArchiveFlagEqualTo(ArchiveFlagEnum.STR_0.getCode());
        flowOrderMapper.updateByExample(flowOrder, flowOrderExample);
    }

    /**
     * 下单结果转换
     *
     * @param weChatXmlUtil
     * @return
     */
    public FlowOrderRes resultConversion(WeChatXmlUtil weChatXmlUtil) {
        String packageStr = Joiner.on("=").join("prepay_id", weChatXmlUtil.getPrepay_id());
        FlowOrderRes flowOrderRes = new FlowOrderRes();
        flowOrderRes.setAppId(weChatXmlUtil.getAppid());
        flowOrderRes.setTimeStamp(System.currentTimeMillis());
        flowOrderRes.setNonceStr(MD5Util.MD5Encode(Joiner.on("&").join(weChatXmlUtil.getPrepay_id(), PublicConfig.MCH_KEY)));
        flowOrderRes.setPack_age(packageStr);
        flowOrderRes.setSignType(PublicConfig.SIGN_TYPE);
        try {
            flowOrderRes.setPaySign(WeChatXmlUtil.getSign(BeanToMapUtil.convertBean(flowOrderRes), PublicConfig.MCH_KEY));
        } catch (Exception e) {
            log.error("resultConversion exception,error", e);
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10003.getResCode(), e.getMessage());
        }
        return flowOrderRes;
    }
}
