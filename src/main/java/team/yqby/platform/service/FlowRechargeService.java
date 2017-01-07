package team.yqby.platform.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.yqby.platform.common.util.BeanToMapUtil;
import team.yqby.platform.common.util.DateUtil;
import team.yqby.platform.common.util.MD5Util;
import team.yqby.platform.common.util.WeChatXmlUtil;
import team.yqby.platform.config.PublicConfig;
import team.yqby.platform.dto.model.FlowOrder;
import team.yqby.platform.dto.model.FlowStock;
import team.yqby.platform.dto.model.req.FlowOrderReq;
import team.yqby.platform.dto.model.req.PayNotifyReq;
import team.yqby.platform.dto.model.res.FlowOrderRes;
import team.yqby.platform.dto.model.res.PayNotifyRes;
import team.yqby.platform.manager.FlowRechargeManager;
import team.yqby.platform.manager.FlowWeChatManager;

import java.text.ParseException;
import java.util.Date;

/**
 * <p>
 * 流量充值服务
 * </p>
 * User：jumping Date： 2017/1/2 0002 Version：1.0
 */
@Service
@Slf4j
public class FlowRechargeService {

    @Autowired
    private FlowWeChatManager flowWeChatManager;
    @Autowired
    private FlowRechargeManager flowRechargeManager;

    /**
     * 流量充值下单
     *
     * @param flowOrderReq 下单请求对象
     * @return
     */
    public FlowOrderRes createOrder(FlowOrderReq flowOrderReq) {
        //1.校验商品价格
        FlowStock flowStock = flowWeChatManager.checkGoodsPrice(flowOrderReq.getFlowID(), flowOrderReq.getFlowCurrentCost());

        //2.支付下单
        String orderNo = flowWeChatManager.createPayOrder(flowStock, flowOrderReq.getPhone(), flowOrderReq.getOpenID());

        //3.微信下单
        WeChatXmlUtil weChatXmlUtil = flowWeChatManager.createWeChatOrder(flowOrderReq.getOpenID(), orderNo, flowOrderReq.getFlowCurrentCost());

        //4.返回结果转换
        FlowOrderRes flowOrderRes = flowWeChatManager.resultConversion(weChatXmlUtil);
        return flowOrderRes;
    }

    /**
     * 支付回调通知
     *
     * @param payNotifyReq
     * @return
     */
    public PayNotifyRes payNotify(PayNotifyReq payNotifyReq) throws ParseException {
        //1.校验支付状态更新支付结果
        flowWeChatManager.checkTransSafe(payNotifyReq);
        //2.查询下单信息
        FlowOrder flowOrder = flowWeChatManager.queryPayOrderInfo(payNotifyReq.getOut_trade_no());
        //2.业务下单
        String businessReqNo = flowRechargeManager.createBusinessOrder(payNotifyReq.getOut_trade_no(), flowOrder.getPhone());
        //2.流量充值
        flowRechargeManager.recharge(flowOrder.getPhone(), PublicConfig.FLOW_CHANNEL_ID, flowOrder.getFlowId(), DateUtil.getCurrent(), businessReqNo);
        return new PayNotifyRes();
    }
}
