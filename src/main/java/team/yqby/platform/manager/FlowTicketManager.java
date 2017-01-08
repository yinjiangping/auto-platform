package team.yqby.platform.manager;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;
import team.yqby.platform.common.WebCall;
import team.yqby.platform.config.PublicConfig;
import team.yqby.platform.dto.Response;
import team.yqby.platform.dto.model.res.FlowOpenIDRes;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: luwanchuan
 * Date: 2017/1/8
 */
@Service
@Slf4j
public class FlowTicketManager {

    /**
     * 根据code获取openID
     * @param code
     * @return
     */
    public Response<FlowOpenIDRes> queryByCode (String code) {
        Response response;
        try {
            List<NameValuePair> formParams = new ArrayList<>();
            formParams.add(new BasicNameValuePair("appid", PublicConfig.APP_ID));
            formParams.add(new BasicNameValuePair("secret", PublicConfig.APP_SECRET));
            formParams.add(new BasicNameValuePair("code", code));
            formParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
            String resString = WebCall.closeableHttpClientPost("https://api.weixin.qq.com/sns/oauth2/access_token", formParams);
            FlowOpenIDRes flowOpenIDRes = JSON.parseObject(resString, FlowOpenIDRes.class);
            log.info("请求微信获取openID，参数code：{}, 响应结果：{}", code, flowOpenIDRes);
            response = new Response(flowOpenIDRes);
            if (StringUtils.isNotBlank(flowOpenIDRes.getErrcode()) || StringUtils.isNotBlank(flowOpenIDRes.getErrmsg())) {
                response = new Response(flowOpenIDRes.getErrcode(), flowOpenIDRes.getErrmsg());
            }
        } catch (Exception e) {
            log.error("请求微信获取openID，参数code：{}, 发生异常：{}", code, e);
            response = new Response("error", "请求微信获取openID发生异常");
        }

        return response;
    }

}
