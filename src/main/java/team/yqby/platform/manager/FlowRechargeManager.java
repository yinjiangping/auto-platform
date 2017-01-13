package team.yqby.platform.manager;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
import team.yqby.platform.dto.model.req.BizNotifyReq;
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
     * @param orderNo  订单号
     * @param phone    手机号
     * @param orderAmt 订单金额
     * @return
     */
    public String createBusinessOrder(String orderNo, String phone, Long orderAmt) {
        FlowBizTrans flowBizTrans = new FlowBizTrans();
        flowBizTrans.setBizId(NumberUtil.getBizOrderNoRandom());
        flowBizTrans.setOrderId(orderNo);
        flowBizTrans.setPhone(phone);
        flowBizTrans.setCurrentCost(orderAmt);
        flowBizTrans.setTransStatus(TransStatusEnum.INI.getStatus());
        flowBizTrans.setCheckStatus(CheckStatusEnum.STR_0.getCode());
        flowBizTrans.setArchiveFlag(ArchiveFlagEnum.STR_0.getCode());
        flowBizTrans.setCreateBy(PublicConfig.SYS_USER);
        flowBizTrans.setCreateDate(new Date());
        flowBizTrans.setUpdateDate(new Date());
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
            updateStatusByOrderId(channelOrderId, TransStatusEnum.RECHARGE_FAIL.getStatus(), flowRechargeRes.getRet(), flowRechargeRes.getMsg(), new Date(), flowRechargeRes.getFlowrecord());
            throw new AutoPlatformException(flowRechargeRes.getRet(), flowRechargeRes.getMsg());
        }
        updateStatusByOrderId(channelOrderId, TransStatusEnum.RECHARGE_SUC.getStatus(), flowRechargeRes.getRet(), flowRechargeRes.getMsg(), new Date(), flowRechargeRes.getFlowrecord());
        return new PayNotifyRes(PublicConfig.CALL_SUCCESS, PublicConfig.CALL_SUCCESS);
    }

    /**
     * 更新交易状态
     *
     * @param channelOrderId 渠道订单号(业务请求订单号)
     * @param transStatus    交易状态
     */
    public void updateStatusByOrderId(String channelOrderId, String transStatus, String bizRespCode, String bizRespDesc, Date bizRespTime, String bizResNo) {
        FlowBizTrans flowBizTrans = new FlowBizTrans();
        flowBizTrans.setTransStatus(transStatus);
        flowBizTrans.setBizRespId(bizResNo);
        flowBizTrans.setBizRespCode(bizRespCode);
        flowBizTrans.setBizRespDesc(bizRespDesc);
        flowBizTrans.setBizRespTime(bizRespTime);
        FlowBizTransExample flowBizTransExample = new FlowBizTransExample();
        FlowBizTransExample.Criteria criteria = flowBizTransExample.createCriteria();
        //根据渠道订单号(业务请求订单号)更新业务状态信息【防止订单号重复更新非成功的订单】
        criteria.andBizIdEqualTo(channelOrderId).andTransStatusNotEqualTo(TransStatusEnum.RECHARGE_SUC.getStatus()).andArchiveFlagEqualTo(ArchiveFlagEnum.STR_0.getCode());
        int i = flowBizTransMapper.updateByExampleSelective(flowBizTrans, flowBizTransExample);
        if (i == 0) {
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10007.getResCode(), ServiceErrorCode.ERROR_CODE_A10007.getResDesc());
        }
    }

    /**
     * 更新交易状态
     *
     * @param channelOrderId 渠道订单号(业务请求订单号)
     * @param transStatus    交易状态
     */
    public void updateStatusByReqNoAndResNo(String channelOrderId, String bizResNo, String transStatus, String bizRespCode, String bizRespDesc, Date bizRespTime) {
        FlowBizTrans flowBizTrans = new FlowBizTrans();
        flowBizTrans.setTransStatus(transStatus);
        flowBizTrans.setBizRespCode(bizRespCode);
        flowBizTrans.setBizRespDesc(bizRespDesc);
        flowBizTrans.setBizRespTime(bizRespTime);
        FlowBizTransExample flowBizTransExample = new FlowBizTransExample();
        FlowBizTransExample.Criteria criteria = flowBizTransExample.createCriteria();
        //根据渠道订单号(业务请求订单号)更新业务状态信息【防止订单号重复更新非成功的订单】
        criteria.andBizIdEqualTo(channelOrderId).andBizRespIdNotEqualTo(bizResNo).andTransStatusNotEqualTo(TransStatusEnum.RECHARGE_SUC.getStatus()).andArchiveFlagEqualTo(ArchiveFlagEnum.STR_0.getCode());
        log.info("updateStatusByReqNoAndResNo request param:{}", flowBizTransExample);
        int i = flowBizTransMapper.updateByExampleSelective(flowBizTrans, flowBizTransExample);
        if (i == 0) {
            log.info("updateStatusByReqNoAndResNo error,bizResNo:{}", bizResNo);
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10007.getResCode(), ServiceErrorCode.ERROR_CODE_A10007.getResDesc());
        }
    }

    /**
     * 查询业务订单结果
     *
     * @param bizReqNo 渠道订单号
     * @param bizResNo 业务订单号
     * @return
     */
    public FlowBizTrans queryBizOrderInfo(String bizReqNo, String bizResNo) {
        FlowBizTransExample flowBizTransExample = new FlowBizTransExample();
        FlowBizTransExample.Criteria criteria = flowBizTransExample.createCriteria();
        criteria.andBizIdEqualTo(bizReqNo).andArchiveFlagEqualTo(ArchiveFlagEnum.STR_0.getCode());
        if (StringUtils.isNotEmpty(bizResNo)) {
            criteria.andBizRespIdEqualTo(bizResNo);
        }
        log.info("queryBizInfo request param:{}", flowBizTransExample);
        List<FlowBizTrans> flowBizTransList = flowBizTransMapper.selectByExample(flowBizTransExample);
        if (flowBizTransList == null || flowBizTransList.isEmpty()) {
            log.error("queryBizInfo result is null,bizResNo:{}", bizResNo);
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10006);
        }
        return flowBizTransList.get(0);
    }

    /**
     * 查询业务订单结果
     *
     * @param bizReqNo 渠道订单号
     * @param bizResNo 业务订单号
     * @return
     */
    public void queryBizOrderResult(String bizReqNo, String bizResNo) {
        FlowBizTransExample flowBizTransExample = new FlowBizTransExample();
        FlowBizTransExample.Criteria criteria = flowBizTransExample.createCriteria();
        criteria.andBizIdEqualTo(bizReqNo).andArchiveFlagEqualTo(ArchiveFlagEnum.STR_0.getCode());
        if (StringUtils.isNotEmpty(bizResNo)) {
            criteria.andBizRespIdEqualTo(bizResNo);
        }
        log.info("queryBizInfo request param:{}", flowBizTransExample);
        List<FlowBizTrans> flowBizTransList = flowBizTransMapper.selectByExample(flowBizTransExample);
        if (flowBizTransList != null && !flowBizTransList.isEmpty()) {
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10011);
        }

    }

    /**
     * 更新业务交易状态
     *
     * @param bizNotifyReq 业务通知请求参数
     * @return
     */
    public void updateBizTransStatus(BizNotifyReq bizNotifyReq) {
        TransStatusEnum transStatusEnum = null;
        if ("000".equals(bizNotifyReq.getResult())) {
            transStatusEnum = TransStatusEnum.RECHARGE_SUC;
        } else {
            transStatusEnum = TransStatusEnum.RECHARGE_FAIL;
        }
        updateStatusByReqNoAndResNo(bizNotifyReq.getChannelorderid(), bizNotifyReq.getFlowrecord(), transStatusEnum.getStatus(), bizNotifyReq.getResult(), transStatusEnum.getDesc(), DateUtil.parse(bizNotifyReq.getTimestamp(), DateUtil.fullPattern));

    }

    /**
     * 更新业务交易状态
     *
     * @param bizNotifyReq 业务通知请求参数
     * @return
     */
    public void checkBizParam(BizNotifyReq bizNotifyReq) {
        String sign = MD5Util.MD5Encode(Joiner.on("").join(bizNotifyReq.getChannelid(), bizNotifyReq.getFlowrecord(), bizNotifyReq.getTimestamp(), bizNotifyReq.getResult(), MD5Util.MD5Encode(MD5Util.MD5Encode(PublicConfig.FLOW_KEY))));
        if (!bizNotifyReq.getSign().equals(sign)) {
            log.error("sign check fail,param request sign:{},param create sign:{}", bizNotifyReq.getSign(), sign);
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10005);
        }
    }

}
