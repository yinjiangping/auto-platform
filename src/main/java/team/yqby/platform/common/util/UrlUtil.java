package team.yqby.platform.common.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.httpclient.NameValuePair;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * URL请求
 * </p>
 * User：jumping Date： 2016/10/19 0019  Version：1.0
 */
public class UrlUtil {
    public static HttpURLConnection hc = null;
    private static String sendCoding = "UTF-8";
    public static final String ParseCode = "UTF-8";
    public static String cookie = "JSESSIONID=D0357157545A75D2CBE7FD5BC2BB6B22";

    public static void initCon(String reqUrl) throws Exception {
        URL url = new URL(reqUrl);
        hc = (HttpURLConnection) url.openConnection();
        hc.setDoOutput(true);
        hc.setRequestMethod("POST");
//        hc.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
//        hc.setRequestProperty("Accept-Encoding", "gzip, deflate");
//        hc.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
//        hc.setRequestProperty("Connection", "keep-alive");
//        hc.setRequestProperty("Content-Length", "10");
        hc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        hc.setRequestProperty("Cookie", UrlUtil.cookie);
        hc.setRequestProperty("Host", "service.sh.189.cn");
        hc.setRequestProperty("Origin", "http://service.sh.189.cn");
        hc.setRequestProperty("Referer", "http://service.sh.189.cn/service/pay/bill");
        hc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.108 Safari/537.36");
//        hc.setRequestProperty("X-Requested-With", "XMLHttpRequest");
    }

    public static void sendPost(String postData) throws Exception {
        OutputStream os = hc.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, sendCoding);
        osw.write(postData);
        osw.flush();
        osw.close();
        os.close();
    }

    public static String readData() throws IOException {
        int code = hc.getResponseCode();
        StringBuffer sb = null;
        if (code == HttpURLConnection.HTTP_OK) {
            sb = new StringBuffer();
            InputStream is = hc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, ParseCode);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            do {
                line = br.readLine();
                if (line != null && !line.equals("")) {
                    line = line.trim();
                    line = line.replaceAll("&nbsp;", "");
                    sb.append(line);
                }
            } while (line != null);
            br.close();
            isr.close();
            is.close();
            return sb.toString();
        } else
            return null;
    }

    public static String createHtml(String newUrl,NameValuePair [] nameValuePairs)
            throws Exception {
        HttpClient client=new HttpClient();
        PostMethod post = new PostMethod(newUrl);
        post.setRequestHeader("Cookie", UrlUtil.cookie);
        post.addParameters(nameValuePairs);
        client.executeMethod(post);
        post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        String content = post.getResponseBodyAsString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        IOUtils.write(content, new FileOutputStream("d:/" + format.format(new Date()) + "1.html"), "GBK");
        IOUtils.write(content, new FileOutputStream("d:/" + format.format(new Date()) + "2.html"), "UTF-8");
        post.releaseConnection();
        return content;
    }


    public static void saveData(String filePath) throws IOException {
        int code = hc.getResponseCode();
        if (code == HttpURLConnection.HTTP_OK) {
            InputStream is = hc.getInputStream();
            StreamUtil.saveBit(is, filePath);
            is.close();
        }
    }

    public static void killConnect() {
        hc.disconnect();
    }

}
