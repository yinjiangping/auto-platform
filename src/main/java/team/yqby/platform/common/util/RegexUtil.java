package team.yqby.platform.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具
 *
 * @author jumping
 * @version 1.0.0
 * @time 2015/7/5
 */
@Slf4j
public class RegexUtil {

    //手机号格式
    public static final String product_regex = "^[1]([3456789][0-9]{1})[0-9]{8}$";

    //时间格式
    public static final String date_regex = "((20)[0-9]{2}[0-9]{4})|(20)[0-9]{2}[0-9]{4}[0-9]{6}";

    //数字格式
    public static final String number_12_regex="[0-9]{12,}";

    public static final String number_8_regex="[0-9]{8,}";

    //短信验证码
    public static final String vailCode_regex = "[0-9]{6}";

    //图片验证码
    public static final String imageCode_regex = "[0-9a-zA-Z]{4,6}";

    //申请验证码类型
    public static final String authChannel_regex = "^[0-9]{1,2}$";

    //图片验证码格式
    public static final String FTP_IMAGE_TYPE = ".bmp|.jpg|.jpeg|.gif|.png|.JPG|.GIF|.JPEG|.PNG|.BMP";

    /**
     * @param inputStr 需要验证的参数
     * @param regex    验证此参数的正则表达式
     * @return boolean
     * @Description: 请求参数正则验证
     */
    public static boolean doRegex(String inputStr, String regex) {
        boolean rst = false;
        /*正则表达式*/
        Pattern pattern = null;
        /*操作的字符串*/
        Matcher matcher = null;
        try {
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(inputStr);
            rst = matcher.matches();
        } catch (Exception e) {
            log.error("正则校验异常,{}", e.getMessage());
            return false;
        }
        return rst;
    }

    public static String doRegexValue(String inputStr, String regex) {
        /*正则表达式*/
        Pattern pattern = null;
        /*操作的字符串*/
        Matcher matcher = null;
        try {
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(inputStr);
            while (matcher.find()) {
                return matcher.group(1);
            }
        } catch (Exception e) {
            log.error("正则校验异常,{}", e.getMessage());
        }
        return "";
    }
}
