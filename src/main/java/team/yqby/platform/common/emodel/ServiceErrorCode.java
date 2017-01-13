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
    ERROR_CODE_A10005("A10005", "签名有误"),
    ERROR_CODE_A10006("A10006", "未找到订单信息"),
    ERROR_CODE_A10007("A10007", "数据库异常"),
    ERROR_CODE_A10008("A10008", "获取签名失败"),
    ERROR_CODE_A10009("A10009", "用户未登陆"),
    ERROR_CODE_A10010("A10010", "订单未支付成功"),
    ERROR_CODE_A10011("A10011", "已回调通知过"),

    ERROR_CODE_F99999("F99999", "服务器繁忙，请稍后重试!"),
    ERROR_CODE_F88888("F88888", "正在受理请求中"),
    ERROR_CODE_F777777("F77777", "当前无法操作");
    private String resCode;
    private String resDesc;
}
