package team.yqby.platform.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.yqby.platform.common.util.BeanToMapUtil;
import team.yqby.platform.common.util.MD5Util;
import team.yqby.platform.common.util.WeChatXmlUtil;
import team.yqby.platform.config.PublicConfig;
import team.yqby.platform.dto.model.FlowStock;
import team.yqby.platform.dto.model.req.FlowOrderReq;
import team.yqby.platform.dto.model.res.FlowOrderRes;
import team.yqby.platform.manager.FlowRechargeManager;
import team.yqby.platform.manager.FlowWeChatManager;

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

    /**
     * 流量充值下单
     *
     * @param flowOrderReq 下单请求对象
     * @return
     */
    public FlowOrderRes createOrder(FlowOrderReq flowOrderReq) {
        //1.校验商品价格
        FlowStock flowStock = flowWeChatManager.checkGoodsPrice(String.valueOf(flowOrderReq.getFlowID()), flowOrderReq.getFlowCurrentCost());
        //2.支付下单
        String orderNo = flowWeChatManager.createPayOrder(flowStock, flowOrderReq.getPhone(), flowOrderReq.getOpenID());
        //3.微信下单
        WeChatXmlUtil weChatXmlUtil = flowWeChatManager.createWeChatOrder(flowOrderReq.getOpenID(), orderNo, flowOrderReq.getFlowCurrentCost());
        //4.返回结果转换
        FlowOrderRes flowOrderRes = flowWeChatManager.resultConversion(weChatXmlUtil);
        return flowOrderRes;
    }
}
