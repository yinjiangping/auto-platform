package team.yqby.platform.controller;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.util.DateParseException;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import team.yqby.platform.common.emodel.ServiceErrorCode;
import team.yqby.platform.common.util.DateUtil;
import team.yqby.platform.common.util.ParamsValidate;
import team.yqby.platform.common.util.StreamUtil;
import team.yqby.platform.config.ApiUrls;
import team.yqby.platform.dto.Response;
import team.yqby.platform.dto.model.req.FlowOrderReq;
import team.yqby.platform.dto.model.req.PayNotifyReq;
import team.yqby.platform.dto.model.res.FlowOrderRes;
import team.yqby.platform.dto.model.res.PayNotifyRes;
import team.yqby.platform.exception.AutoPlatformException;
import team.yqby.platform.manager.FlowWeChatManager;
import team.yqby.platform.service.FlowRechargeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

@Slf4j
@RestController
public class FlowRechargeController {
    @Autowired
    private FlowRechargeService flowRechargeService;

    @RequestMapping(value = ApiUrls.FLOW_CREATE_ORDER_URL, method = RequestMethod.POST)
    public
    @ResponseBody
    Response<FlowOrderRes> createOrder(@Valid @RequestBody FlowOrderReq flowOrderReq, Errors errors) {
        FlowOrderRes flowOrderRes = null;
        try {
            log.info("createOrder started, request params:{}", flowOrderReq);
            //校验参数
            ParamsValidate.validParamError(errors);
            //生成订单
            flowOrderRes = flowRechargeService.createOrder(flowOrderReq);
            log.info("createOrder finished, openId:{}, response:{}", flowOrderReq.getOpenID(), flowOrderRes);
        } catch (AutoPlatformException e) {
            log.error("createOrder meet error, openId:{}, response:{}", flowOrderReq.getOpenID(), Throwables.getStackTraceAsString(e));
            return new Response<>(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("createOrder meet error, openId:{}, response:{}", flowOrderReq.getOpenID(), Throwables.getStackTraceAsString(e));
            return new Response<>(ServiceErrorCode.ERROR_CODE_F99999);
        }
        return new Response<>(flowOrderRes);
    }


    @RequestMapping(value = ApiUrls.FLOW_PAY_NOTIFY_URL)
    public
    @ResponseBody
    PayNotifyRes payNotify(HttpServletRequest request) {
        try {
            String requestXml = StreamUtil.streamToStr(request);
            log.info("payNotify started, request params:{}", requestXml);
            //1.XML请求报文转对象
            PayNotifyReq payNotifyReq = PayNotifyReq.fromXML(requestXml);
            //2.通知结果处理(校验支付状态并充值)
            PayNotifyRes payNotifyRes = flowRechargeService.payNotify(payNotifyReq);
            log.info("payNotify finished, payNotifyReq:{}, response:{}", payNotifyRes);
            return payNotifyRes;
        } catch (AutoPlatformException e) {
            log.error("payNotify meet error, ", Throwables.getStackTraceAsString(e));
            return new PayNotifyRes(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("payNotify meet error, ", Throwables.getStackTraceAsString(e));
            return new PayNotifyRes(ServiceErrorCode.ERROR_CODE_F99999.getResCode(), ServiceErrorCode.ERROR_CODE_F99999.getResDesc());
        }

    }

    public static void main(String[] args) throws JDOMException, IOException, IllegalAccessException, IntrospectionException, InvocationTargetException, DateParseException, ParseException {
        String xml = "<xml>\n" +
                "  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "  <attach><![CDATA[支付测试]]></attach>\n" +
                "  <bank_type><![CDATA[CFT]]></bank_type>\n" +
                "  <fee_type><![CDATA[CNY]]></fee_type>\n" +
                "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "  <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
                "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
                "  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
                "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\n" +
                "  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>\n" +
                "  <time_end><![CDATA[20140903131540]]></time_end>\n" +
                "  <total_fee>1</total_fee>\n" +
                "  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
                "</xml> ";
        PayNotifyReq payNotifyReq = PayNotifyReq.fromXML(xml);
        new FlowWeChatManager().checkTransSafe(payNotifyReq);


        System.out.println(payNotifyReq);
    }


}
