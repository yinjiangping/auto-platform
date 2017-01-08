package team.yqby.platform.manager;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.yqby.platform.common.WebCall;
import team.yqby.platform.common.emodel.ServiceErrorCode;
import team.yqby.platform.common.enums.ArchiveFlagEnum;
import team.yqby.platform.common.enums.CheckStatusEnum;
import team.yqby.platform.common.enums.TransStatusEnum;
import team.yqby.platform.common.util.DateUtil;
import team.yqby.platform.common.util.MD5Util;
import team.yqby.platform.common.util.NumberUtil;
import team.yqby.platform.config.PublicConfig;
import team.yqby.platform.dto.model.*;
import team.yqby.platform.dto.model.inner.FlowRechargeRes;
import team.yqby.platform.dto.model.res.FlowOrderRes;
import team.yqby.platform.dto.model.res.PayNotifyRes;
import team.yqby.platform.exception.AutoPlatformException;
import team.yqby.platform.mapper.FlowBizTransMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * </p>
 * User：jumping Date： 2017/1/2 0002 Version：1.0
 */
@Service
@Slf4j
public class FlowRechargeManager {

    @Autowired
    private FlowBizTransMapper flowBizTransMapper;

    /**
     * 业务下单
     *
     * @param orderNo 订单号
     * @param phone   手机号
     * @return
     */
    public String createBusinessOrder(String orderNo, String phone) {
        FlowBizTrans flowBizTrans = new FlowBizTrans();
        flowBizTrans.setBizId(NumberUtil.getOrderNoRandom());
        flowBizTrans.setOrderId(orderNo);
        flowBizTrans.setPhone(phone);
        flowBizTrans.setTransStatus(TransStatusEnum.INI.getStatus());
        flowBizTrans.setCheckStatus(CheckStatusEnum.STR_0.getCode());
        flowBizTrans.setArchiveFlag(ArchiveFlagEnum.STR_0.getCode());
        flowBizTrans.setCreateBy(PublicConfig.SYS_USER);
        flowBizTrans.setCreateDate(new Date());
        flowBizTrans.setArchiveFlag(ArchiveFlagEnum.STR_0.getCode());
        int i = flowBizTransMapper.insert(flowBizTrans);
        if (i == 0) {
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10003);
        }
        return flowBizTrans.getBizId();
    }

    /**
     * 流量充值
     *
     * @param phone          手机号
     * @param channelId      渠道编号
     * @param productId      产品编号
     * @param channelOrderId 渠道订单号
     * @return
     */
    public PayNotifyRes recharge(String phone, String channelId, String productId, String timestamp, String channelOrderId) {
        List<NameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair("phone", phone));
        formParams.add(new BasicNameValuePair("channelid", channelId));
        formParams.add(new BasicNameValuePair("productid", productId));
        formParams.add(new BasicNameValuePair("timestamp", timestamp));
        formParams.add(new BasicNameValuePair("channelorderid", channelOrderId));
        String md5Str = MD5Util.MD5Encode(Joiner.on("").join(channelId, productId, phone, timestamp, MD5Util.MD5Encode(MD5Util.MD5Encode(PublicConfig.FLOW_KEY))));
        formParams.add(new BasicNameValuePair("sign", md5Str));
        String resString = WebCall.closeableHttpClientPost(PublicConfig.FLOW_RECHARGE_URL, formParams);
        FlowRechargeRes flowRechargeRes = JSON.parseObject(resString, FlowRechargeRes.class);
        if (!"000".equals(flowRechargeRes.getRet())) {
            updateBusinessStatus(channelOrderId,TransStatusEnum.RECHARGE_FAIL.getStatus(),flowRechargeRes.getRet(),flowRechargeRes.getMsg(),new Date());
            throw new AutoPlatformException(flowRechargeRes.getRet(), flowRechargeRes.getMsg());
        }
        updateBusinessStatus(channelOrderId,TransStatusEnum.RECHARGE_SEND.getStatus(),flowRechargeRes.getRet(),flowRechargeRes.getMsg(),new Date());
        return new PayNotifyRes(flowRechargeRes.getRet(), flowRechargeRes.getMsg());
    }

    /**
     * 更新交易状态
     *
     * @param orderNo
     * @param transStatus
     */
    public void updateBusinessStatus(String orderNo, String transStatus, String bizRespCode, String bizRespDesc, Date bizRespTime) {
        FlowBizTrans flowBizTrans = new FlowBizTrans();
        flowBizTrans.setTransStatus(transStatus);
        flowBizTrans.setBizRespCode(bizRespCode);
        flowBizTrans.setBizRespDesc(bizRespDesc);
        flowBizTrans.setBizRespTime(bizRespTime);
        FlowBizTransExample flowBizTransExample = new FlowBizTransExample();
        FlowBizTransExample.Criteria criteria = flowBizTransExample.createCriteria();
        criteria.andOrderIdEqualTo(orderNo).andArchiveFlagEqualTo(ArchiveFlagEnum.STR_0.getCode());
        int i = flowBizTransMapper.updateByExampleSelective(flowBizTrans, flowBizTransExample);
        if (i == 0) {
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10007.getResCode(), ServiceErrorCode.ERROR_CODE_A10007.getResDesc());
        }
    }

}
