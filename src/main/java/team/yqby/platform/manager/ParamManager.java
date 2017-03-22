package team.yqby.platform.manager;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import team.yqby.platform.common.constant.SystemConstant;
import team.yqby.platform.exception.AutoPlatformException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * <p>
 * </p>
 * User：jumping Date： 2017/3/18 0018 Version：1.0
 */
@Slf4j
public class ParamManager {
    /**
     * 检查参数不能为空
     *
     * @param paramKey
     * @param paramValue
     */
    public static void checkParam(String paramKey, String paramValue) {
        if (StringUtils.isEmpty(paramKey)) {
            throw new AutoPlatformException(Joiner.on("").join(paramValue, "不能为空"));
        }
    }

    /***
     * 参数通过UTF-8解码
     * @param paramKey
     * @return
     */
    public static String strDecode(String paramKey){
        String newParamKey = paramKey;
        try {
            newParamKey = URLDecoder.decode(paramKey, SystemConstant.UTF_8);
        } catch (UnsupportedEncodingException e) {
            log.error("Parameter {} decoding exception, ",newParamKey,e);
        }
        return newParamKey;
    }
}
