package team.yqby.platform.common.emodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 错误枚举
 * </p>
 * User：jumping Date： 2016/11/5 0005 Version：1.0
 */
@Getter
@AllArgsConstructor
public enum ServiceErrorCode {
    ERROR_CODE_A10001("A10001", "商品信息不存在"),
    ERROR_CODE_A10002("A10002", "支付金额有误，请重新下单"),
    ERROR_CODE_A10003("A10003", "下单失败"),
    ERROR_CODE_A10004("A10004", "参数错误"),

    ERROR_CODE_F99999("F99999", "服务器繁忙，请稍后重试!"),
    ERROR_CODE_F88888("F88888", "正在受理请求中"),
    ERROR_CODE_F777777("F77777", "当前无法操作");
    private String resCode;
    private String resDesc;
}
