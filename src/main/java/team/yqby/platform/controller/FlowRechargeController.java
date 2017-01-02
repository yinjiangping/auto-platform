package team.yqby.platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.web.bind.annotation.*;
import team.yqby.platform.dto.Response;
import team.yqby.platform.dto.model.req.FlowOrderReq;
import team.yqby.platform.dto.model.res.FlowOrderRes;

import java.util.Date;

@Slf4j
@RestController
public class FlowRechargeController {

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public
    @ResponseBody
    Response<FlowOrderRes> createOrder(@RequestBody FlowOrderReq flowOrderReq) {

        return null;
    }

}
