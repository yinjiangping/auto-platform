package team.yqby.platform.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import team.yqby.platform.common.emodel.ServiceErrorCode;
import team.yqby.platform.common.util.ParamsValidate;
import team.yqby.platform.config.ApiUrls;
import team.yqby.platform.dto.Response;
import team.yqby.platform.dto.model.req.FlowOrderReq;
import team.yqby.platform.dto.model.res.FlowOrderRes;
import team.yqby.platform.exception.AutoPlatformException;
import team.yqby.platform.service.FlowRechargeService;

import javax.validation.Valid;
import java.util.Date;

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
            return new Response<FlowOrderRes>(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("createOrder meet error, openId:{}, response:{}", flowOrderReq.getOpenID(), Throwables.getStackTraceAsString(e));
            return new Response<FlowOrderRes>(ServiceErrorCode.ERROR_CODE_F99999);
        }
        return new Response<FlowOrderRes>(flowOrderRes);
    }

}
