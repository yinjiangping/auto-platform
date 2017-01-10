package team.yqby.platform.common.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import team.yqby.platform.common.emodel.ServiceErrorCode;
import team.yqby.platform.config.PublicConfig;
import team.yqby.platform.exception.AutoPlatformException;

import java.security.DigestException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * XML转换工具
 *
 * @author jumping
 * @version 1.0.0
 * @time 2015/6/30
 */
@Slf4j
@Getter
@Setter
@ToString
public class WeChatXmlUtil {

    private String return_code;

    private String return_msg;

    private String appid;

    private String mch_id;

    private String nonce_str;

    private String openid;

    private String sign;

    private String result_code;

    private String prepay_id;

    private String trade_type;

    private String code_url;

    private String err_code;

    private String err_code_des;

    private String originXML;

    /**
     * 生成XML报文
     *
     * @param object 对象
     * @return 报文
     */
    public static String toXml(Object object) {
        XStream xStream = new XStream(new StaxDriver());
        xStream.autodetectAnnotations(true);
        String xml = xStream.toXML(object);
        return xml;
    }

    /**
     * XML解析转换对象
     *
     * @param xml
     * @return
     * @throws org.dom4j.DocumentException
     */
    public static WeChatXmlUtil fromXML(String xml) throws DocumentException {
        WeChatXmlUtil result = new WeChatXmlUtil();
        result.setOriginXML(xml);
        Element root = DocumentHelper.parseText(xml).getRootElement();
        String returnCode = root.elementTextTrim("return_code");
        String resultCode = root.elementTextTrim("result_code");
        String returnMsg = root.elementTextTrim("return_msg");
        result.setResult_code(returnCode);
        result.setResult_code(resultCode);
        if (!PublicConfig.CALL_SUCCESS.equals(returnCode)) {
            throw new AutoPlatformException(returnCode, returnMsg);
        }
        if (!PublicConfig.CALL_SUCCESS.equals(resultCode)) {
            throw new AutoPlatformException(root.elementTextTrim("err_code"), root.elementTextTrim("err_code_des"));
        }
        result.setPrepay_id(root.elementTextTrim("prepay_id"));
        result.setTrade_type(root.elementTextTrim("trade_type"));
        result.setCode_url(root.elementTextTrim("code_url"));
        result.setAppid(root.elementTextTrim("appid"));
        result.setMch_id(root.elementTextTrim("mch_id"));
        result.setNonce_str(root.elementTextTrim("nonce_str"));
        result.setOpenid(root.elementTextTrim("openid"));
        result.setSign(root.elementTextTrim("sign"));
        return result;
    }

    public static String getSign(Map<String, String> params, String key) {
        Map<String, Object> sortMap = new TreeMap<String, Object>();
        sortMap.putAll(params);
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Object> s : sortMap.entrySet()) {
            String k = s.getKey();
            Object v = s.getValue();
            if (StringUtils.isBlank(String.valueOf(v))) {
                continue;
            }
            builder.append(k.replace("_gjz","")).append("=").append(v).append("&");
        }
        if (!sortMap.isEmpty()) {
            builder.deleteCharAt(builder.length() - 1);
        }
        builder.append("&").append("key").append("=").append(key);
        return MD5Util.MD5Encode(builder.toString()).toUpperCase();
    }

    public static String getSha1Sign(Map<String, Object> params, String key) {
        String sha1SignStr = "";
        try {
            params.put("key", key);
            sha1SignStr = Sha1Util.SHA1(params);
        } catch (DigestException e) {
            log.error("getSha1Sign exception ,error", e);
        }
        return sha1SignStr;
    }
}
