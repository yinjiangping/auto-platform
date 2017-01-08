package team.yqby.platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.yqby.platform.common.enums.ErrorCodeEnum;
import team.yqby.platform.dto.Response;
import team.yqby.platform.dto.query.FlowOpenIDDto;
import team.yqby.platform.service.FlowTicketService;

/**
 * ticket业务
 * Author: luwanchuan
 * Date: 2017/1/8
 */
@Slf4j
@Controller
public class FlowTicketController {

    @Autowired
    private FlowTicketService flowTicketService;

    /**
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/queryOpenID", method = RequestMethod.POST)
    @ResponseBody
    public Response<FlowOpenIDDto> queryByCode (String code) {
        Response response;
        Response<String> stringResponse = flowTicketService.queryOpenIDByCode(code);
        if (stringResponse.isSuccess()) {
            FlowOpenIDDto flowOpenIDDto = new FlowOpenIDDto();
            flowOpenIDDto.setOpenID(stringResponse.getResult());
            response = new Response(flowOpenIDDto);
        } else {
            response = new Response(ErrorCodeEnum.SYSTEM_ERROR.getCode(),
                    ErrorCodeEnum.SYSTEM_ERROR.getDesc());
        }
        return response;
    }

}
