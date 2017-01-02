package team.yqby.platform.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import team.yqby.platform.common.emodel.ServiceErrorCode;
import team.yqby.platform.common.util.OCRHelper;
import team.yqby.platform.common.util.UrlUtil;
import team.yqby.platform.exception.AutoPlatformException;

import java.io.*;
import java.net.*;
import java.rmi.RemoteException;
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

    public static String xmlSyncSend(String wsAddress, String xml) throws AutoPlatformException {
        String resXml = "";
        try {
            //服务的地址
            URL wsUrl = new URL(wsAddress);
            HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
            OutputStream os = conn.getOutputStream();
            os.write(xml.getBytes());
            InputStream is = conn.getInputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = is.read(b)) != -1) {
                String ss = new String(b, 0, len, "UTF-8");
                resXml += ss;
            }
            is.close();
            os.close();
            conn.disconnect();
        } catch (Exception e) {
            log.error("send XML request  Exception:{}", e);
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_F99999.getResCode(), e.getMessage());
        }
        return resXml;
    }

    /**
     * HTTP同步POST请求
     *
     * @param url        请求地址
     * @param formParams 请求参数
     * @return
     */
    public static String closeableHttpClientPost(String url, List<org.apache.http.NameValuePair> formParams) {
        String resStr = "";
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        RequestConfig config = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(3000).build();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        try {
            log.info("closeableHttpClientPost requestUrl:{},requestParam:{}", url, formParams);
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF_8");
            httpPost.setEntity(entity);
            HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == org.apache.commons.httpclient.HttpStatus.SC_OK) {
                HttpEntity httpEntity = httpResponse.getEntity();
                resStr = EntityUtils.toString(httpEntity, "UTF-8");
            }
            closeableHttpClient.close();
        } catch (Exception e) {
            log.error("closeableHttpClient requestUrl:{},exception,{}", url, e.getMessage());
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_F777777.getResCode(), ServiceErrorCode.ERROR_CODE_F777777.getResDesc());
        }
        log.info("closeableHttpClientPost responseContent:{}", resStr);
        return resStr;
    }
}
