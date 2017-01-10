package team.yqby.platform.service;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import team.yqby.platform.common.util.BeanToMapUtil;
import team.yqby.platform.common.util.MD5Util;
import team.yqby.platform.common.util.RedisUtil;
import team.yqby.platform.common.util.WeChatXmlUtil;
import team.yqby.platform.config.PublicConfig;
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
    private RedisUtil redisUtil;

    @Autowired
    private FlowTicketManager flowTicketManager;

    /**
     * 根据code获取openID
     *
     * @param code
     * @return
     */
    public Response<String> queryOpenIDByCode(String code) {
        Response response;
        Response<FlowOpenIDRes> resResponse = flowTicketManager.queryByCode(code);
        if (resResponse.isSuccess()) {
            String openId = resResponse.getResult().getOpenid();
            redisUtil.set(openId, openId, 60 * 10L);
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
    public PaySignRes queryJsApiTicketEnc(String openId,String url) {

        //1.校验openId是否存在
        flowTicketManager.checkOpenIdIsExpires(redisUtil.get(openId));

        //2.获取access_token
        String accessToken = flowTicketManager.queryAccessToken();

        //3.获取jsApiTicket
        String jsApiTicket = flowTicketManager.queryJssApiTicket(accessToken);

        //4.验证参数签名
        PaySignRes paySignRes = flowTicketManager.getPaySignRes(jsApiTicket,url);

        return paySignRes;
    }

}
