package team.yqby.platform.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 异常信息
 * Author: jumping
 * Date: 2017/1/5
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum TransStatusEnum {
    INI("INI", "初始化"),
    WAIT_PAY("WAIT_PAY", "下单成功"),
    ORDER_FAIL("ORDER_FAIL", "下单失败"),
    PAY_SUC("PAY_SUC", "支付成功"),
    PAY_FAIL("PAY_FAIL", "支付失败"),
    RECHARGE_SEND("RECHARGE_SEND", "发送充值，已受理"),
    RECHARGE_SUC("RECHARGE_SUC", "充值成功"),
    RECHARGE_FAIL("RECHARGE_FAIL", "充值失败");

    private String status;

    private String desc;

}
