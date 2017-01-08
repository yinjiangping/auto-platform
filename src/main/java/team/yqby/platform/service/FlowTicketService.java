package team.yqby.platform.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.yqby.platform.dto.Response;
import team.yqby.platform.dto.model.res.FlowOpenIDRes;
import team.yqby.platform.manager.FlowTicketManager;

/**
 * Author: luwanchuan
 * Date: 2017/1/8
 */
@Service
@Slf4j
public class FlowTicketService {

    @Autowired
    private FlowTicketManager flowTicketManager;

    /**
     * 根据code获取openID
     * @param code
     * @return
     */
    public Response<String> queryOpenIDByCode(String code) {
        Response response;
        Response<FlowOpenIDRes> resResponse = flowTicketManager.queryByCode(code);
        if (resResponse.isSuccess()) {
            response = new Response(resResponse.getResult().getOpenid());
        } else {
            response = new Response(resResponse.getErrorCode(), resResponse.getErrorMsg());
        }
        return response;
    }

}
