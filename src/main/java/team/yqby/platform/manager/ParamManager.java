package team.yqby.platform.manager;

import com.google.common.base.Joiner;
import org.apache.commons.lang.StringUtils;
import team.yqby.platform.exception.AutoPlatformException;

/**
 * <p>
 * </p>
 * User：jumping Date： 2017/3/18 0018 Version：1.0
 */
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
}
