package team.yqby.platform.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.yqby.platform.dto.Response;
import team.yqby.platform.dto.model.res.FlowOpenIDRes;
import team.yqby.platform.dto.model.res.PaySignRes;
import team.yqby.platform.manager.FlowTicketManager;

/**
 * Author: luwanchuan
 * Date: 2017/1/8
 */
@Service
@Slf4j
public class FlowTicketService {
    @Autowired
    private IRedisService iRedisService;

    @Autowired
    private FlowTicketManager flowTicketManager;

    /**
     * 根据code获取openID
     *
     * @param code
     * @return
     */
    public Response<String> queryOpenIDByCode(String code) {
        iRedisService.set("test","111111111",10*10L);
        String test = iRedisService.get("test");
        log.info("test:{}",test);
        Response response;
        Response<FlowOpenIDRes> resResponse = flowTicketManager.queryByCode(code);
        if (resResponse.isSuccess()) {
            String openId = resResponse.getResult().getOpenid();
            iRedisService.set(openId, openId, 60 * 10L);
            response = new Response(openId);
        } else {
            response = new Response(resResponse.getErrorCode(), resResponse.getErrorMsg());
        }

        return response;
    }

    /**
     * 根据openId获取jsApiTicket加密
     *
     * @param openId
     * @return
     */
    public PaySignRes queryJsApiTicketEnc(String openId, String url) {

        //1.校验openId是否存在
        flowTicketManager.checkOpenIdIsExpires(iRedisService.get(openId), openId);

        //2.获取access_token
        String accessToken = flowTicketManager.queryAccessToken();

        //3.获取jsApiTicket
        String jsApiTicket = flowTicketManager.queryJssApiTicket(accessToken);

        //4.验证参数签名
        PaySignRes paySignRes = flowTicketManager.getPaySignRes(jsApiTicket, url);

        return paySignRes;
    }

}
