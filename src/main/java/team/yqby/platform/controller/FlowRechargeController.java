package team.yqby.platform.controller;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import team.yqby.platform.common.emodel.ServiceErrorCode;
import team.yqby.platform.common.enums.TransStatusEnum;
import team.yqby.platform.common.util.*;
import team.yqby.platform.config.ApiUrls;
import team.yqby.platform.config.PublicConfig;
import team.yqby.platform.dto.Response;
import team.yqby.platform.dto.model.req.BizNotifyReq;
import team.yqby.platform.dto.model.req.FlowOrderReq;
import team.yqby.platform.dto.model.req.PayNotifyReq;
import team.yqby.platform.dto.model.res.FlowOrderRes;
import team.yqby.platform.dto.model.res.PayNotifyRes;
import team.yqby.platform.exception.AutoPlatformException;
import team.yqby.platform.service.FlowRechargeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
public class FlowRechargeController {
    @Autowired
    private FlowRechargeService flowRechargeService;

    /**
     * 流量充值下单
     *
     * @param flowOrderReq 流量充值对象
     * @param errors       绑定对象参数错误
     * @return 下单结果
     */
    @RequestMapping(value = ApiUrls.FLOW_CREATE_ORDER_URL, method = RequestMethod.POST)
    public
    @ResponseBody
    Response<FlowOrderRes> createOrder(@Valid @RequestBody FlowOrderReq flowOrderReq, Errors errors) {
        FlowOrderRes flowOrderRes = null;
        try {
            log.info("createOrder started, request params:{}", flowOrderReq);

            //1.校验请求参数
            ParamsValidate.validParamError(errors);

            //2.生成支付订单
            flowOrderRes = flowRechargeService.createOrder(flowOrderReq);

            log.info("createOrder finished, openId:{}, response:{}", flowOrderReq.getOpenID(), flowOrderRes);
        } catch (AutoPlatformException e) {
            log.error("createOrder meet error, openId:{}, response:{}", flowOrderReq.getOpenID(), e);
            return new Response<>(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("createOrder meet error, openId:{}, response:{}", flowOrderReq.getOpenID(), Throwables.getStackTraceAsString(e));
            return new Response<>(ServiceErrorCode.ERROR_CODE_F99999);
        }
        return new Response<>(flowOrderRes);
    }

    /**
     * 支付结果通知
     *
     * @param request 请求参数(XML报文)
     * @return
     */
    @RequestMapping(value = ApiUrls.FLOW_PAY_NOTIFY_URL)
    public
    @ResponseBody
    PayNotifyRes payCallBack(HttpServletRequest request) {
        try {
            String requestXml = StreamUtil.streamToStr(request);
            log.info("payCallBack started, request params:{}", requestXml);

            //1.XML请求报文转对象
            PayNotifyReq payNotifyReq = PayNotifyReq.fromXML(requestXml);

            //2.通知结果处理(更新支付结果并充值)
            PayNotifyRes payNotifyRes = flowRechargeService.payNotify(payNotifyReq);

            log.info("payCallBack finished, payNotifyReq:{}, response:{}", payNotifyRes);
            return payNotifyRes;
        } catch (AutoPlatformException e) {
            log.error("payCallBack meet error, ", e);
            return new PayNotifyRes(PublicConfig.CALL_SUCCESS, PublicConfig.CALL_SUCCESS);
        } catch (Exception e) {
            log.error("payCallBack meet error, ", Throwables.getStackTraceAsString(e));
            return new PayNotifyRes(ServiceErrorCode.ERROR_CODE_F99999.getResCode(), ServiceErrorCode.ERROR_CODE_F99999.getResDesc());
        }
    }


    /**
     * 业务结果通知
     *
     * @param bizNotifyReq 业务通知请求参数
     * @return
     */
    @RequestMapping(value = ApiUrls.FLOW_BIZ_NOTIFY_URL)
    public
    @ResponseBody
    String bizCallBack(@Valid BizNotifyReq bizNotifyReq, Errors errors) {
        String result = PublicConfig.NOTIFY_RES_RESULT;
        try {
            log.info("bizCallBack started, request params:{}", bizNotifyReq);

            //1.校验请求参数
            ParamsValidate.validParamError(errors);

            //2.通知结果处理(更新业务结果)
            flowRechargeService.bizNotify(bizNotifyReq);

            log.info("bizCallBack finished, response:{}", result);
        } catch (AutoPlatformException e) {
            log.error("bizCallBack meet error, ", e);
        } catch (Exception e) {
            log.error("bizCallBack meet error, ", Throwables.getStackTraceAsString(e));
        }
        return result;
    }
}
