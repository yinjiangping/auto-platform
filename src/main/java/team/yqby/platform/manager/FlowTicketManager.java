package team.yqby.platform.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import team.yqby.platform.common.WebCall;
import team.yqby.platform.common.emodel.ServiceErrorCode;
import team.yqby.platform.common.util.BeanToMapUtil;
import team.yqby.platform.common.util.MD5Util;
import team.yqby.platform.common.util.WeChatXmlUtil;
import team.yqby.platform.config.PublicConfig;
import team.yqby.platform.dto.Response;
import team.yqby.platform.dto.model.res.FlowOpenIDRes;
import team.yqby.platform.dto.model.res.PaySignRes;
import team.yqby.platform.exception.AutoPlatformException;

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
     *
     * @param code
     * @return
     */
    public Response<FlowOpenIDRes> queryByCode(String code) {
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

    /**
     * 检验openId是否有效
     */
    public void checkOpenIdIsExpires(Object openId) {
        if (StringUtils.isBlank(String.valueOf(openId))) {
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10009);
        }
    }

    /**
     * 获取ACCESS_TOKEN
     *
     * @return
     */
    @Cacheable(value = "accessTokenCache", keyGenerator = "wiselyKeyGenerator")
    public String queryAccessToken() {
        try {
            String requestParam = Joiner.on("&").join("appid=" + PublicConfig.APP_ID, "secret=" + PublicConfig.APP_SECRET, "grant_type=client_credential");
            log.info("queryAccessToken request:{}", requestParam);
            String resString = WebCall.closeableHttpClientGet("https://api.weixin.qq.com/cgi-bin/token?" + requestParam);
            JSONObject jsonObject = JSON.parseObject(resString);
            log.info("queryAccessToken response:{}", jsonObject);
            String access_token = jsonObject.getString("access_token");
            if (StringUtils.isEmpty(access_token)) {
                throw new AutoPlatformException(jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
            }
            return access_token;
        } catch (Exception e) {
            log.info("queryAccessToken error,", e);
        }
        return "";
    }

    /**
     * 获取ACCESS_TOKEN
     *
     * @return
     */
    @Cacheable(value = "jssApiTicketCache", keyGenerator = "wiselyKeyGenerator")
    public String queryJssApiTicket(String accessToken) {
        try {
            List<NameValuePair> formParams = new ArrayList<>();
            formParams.add(new BasicNameValuePair("access_token", accessToken));
            formParams.add(new BasicNameValuePair("type", "jsapi"));
            log.info("queryJssApiTicket request:{}", formParams);
            String resString = WebCall.closeableHttpClientPost("https://api.weixin.qq.com/cgi-bin/ticket/getticket", formParams);
            JSONObject jsonObject = JSON.parseObject(resString);
            log.info("queryJssApiTicket response:{}", jsonObject);
            String errCode = jsonObject.getString("errcode");
            if (!"0".equals(errCode)) {
                throw new AutoPlatformException(jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
            }
            return jsonObject.getString("ticket");
        } catch (Exception e) {
            log.info("queryJssApiTicket   error,", e);
        }
        return "";
    }

    /**
     * 获取签名返回
     *
     * @param jsApiTicket
     * @return
     */
    public PaySignRes getPaySignRes(String jsApiTicket) {
        PaySignRes paySignRes = new PaySignRes();
        paySignRes.setAppId(PublicConfig.APP_ID);
        paySignRes.setNonceStr(MD5Util.MD5Encode(Joiner.on("&").join(jsApiTicket, PublicConfig.MCH_KEY)));
        paySignRes.setTimeStamp(System.currentTimeMillis());
        paySignRes.setUrl("http://www.djtx.com.cn/main.html");
        paySignRes.setSignature(WeChatXmlUtil.getSha1Sign(BeanToMapUtil.convertBean(paySignRes, ""), PublicConfig.MCH_KEY));
        return paySignRes;
    }
}
