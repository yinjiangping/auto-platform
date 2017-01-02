package team.yqby.platform.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 手机归属服务商
 * Author: luwanchuan
 * Date: 2017/1/2
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum CarrierNameEnum {

    LT("lt", "联通"),
    DX("dx", "电信"),
    YD("yd", "移动");

    private String code;

    private String desc;

}
