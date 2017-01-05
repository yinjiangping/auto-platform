package team.yqby.platform.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import team.yqby.platform.common.emodel.ServiceErrorCode;
import team.yqby.platform.exception.AutoPlatformException;

/**
 * <p>
 *      参数校验公共方法
 * </p>
 * User：jumping Date： 2017/1/5 0005 Version：1.0
 */
@Slf4j
public class ParamsValidate {
    /**
     * 验证码公共参数(包含切面错误)
     *
     * @param errors              错误
     * @return
     */
    public static void validParamError(Errors errors) {
        //字段错误
        if (errors.hasFieldErrors()) {
            log.error("param validate error:{}", errors.getFieldError().getDefaultMessage());
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10004.getResCode(), ServiceErrorCode.ERROR_CODE_A10004.getResDesc());
        }
        //其它错误
        if (errors.hasErrors()) {
            log.error("param other error:{}", errors.getAllErrors().get(0).getDefaultMessage());
            throw new AutoPlatformException(errors.getAllErrors().get(0).getCode(), errors.getAllErrors().get(0).getDefaultMessage());
        }

    }
}
