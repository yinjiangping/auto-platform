package team.yqby.platform.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.util.EntityUtils;
import team.yqby.platform.common.util.OCRHelper;
import team.yqby.platform.common.util.UrlUtil;

import java.io.*;
import java.net.*;
import java.util.List;

/**
 * web请求调用方法
 *
 * @author jumping
 * @version 1.0.0
 * @time 2015/6/29
 */
@Slf4j
public class WebCall {

    /**
     * 获取Cookie数据
     *
     * @param homeUrl 主页地址
     */
    public static void getCookie(String homeUrl) {
        try {
            CookieManager manager = new CookieManager();
            CookieHandler.setDefault(manager);
            URL url = new URL(homeUrl);
            URLConnection connection = url.openConnection();
            connection.getContent();
            CookieStore cookieJar = manager.getCookieStore();
            List<HttpCookie> cookies = cookieJar.getCookies();
            for (HttpCookie cookie : cookies) {
                UrlUtil.cookie = cookie.getValue();
            }

        } catch (Exception e) {
            log.error("主页地址:{} 获取Cookie异常,错误:", homeUrl, e);
        }
    }

    public static void main(String[] args) {
        getCookie("http://service.sh.189.cn");
    }

    /**
     * HTTP POST请求
     *
     * @param homeUrl      主页地址
     * @param interfaceUrl 接口地址
     * @param postData     发送数据
     * @return 接收数据
     */
    public static String postReqUrl(String homeUrl, String interfaceUrl, String postData) {
        try {
            log.info("请求地址:{},发送数据:{}", homeUrl + interfaceUrl, postData);
            UrlUtil.initCon(homeUrl + interfaceUrl);
            UrlUtil.sendPost(postData);
            String buffer = UrlUtil.readData();
            UrlUtil.killConnect();
            log.info("响应数据:{}", buffer);
            return buffer;
        } catch (Exception e) {
            log.error("请求地址:{},出现异常，错误:", homeUrl + interfaceUrl, e);
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 下载图片验证码识别
     *
     * @param homeUrl      主页地址
     * @param interfaceUrl 接口地址
     * @param filePath     图片保存路径
     * @return 图片验证码
     */
    public static String downloadImageReadCode(String homeUrl, String interfaceUrl, String filePath) {
        try {
            UrlUtil.initCon(homeUrl + interfaceUrl);
            UrlUtil.sendPost("");
            UrlUtil.saveData(filePath);
            UrlUtil.killConnect();
            Thread.sleep(2000);
            return new OCRHelper().recognizeText(new File(filePath));
        } catch (Exception e) {
            log.error("下载图片验证码识别异常,错误:", e);
        }
        return "";
    }
}
