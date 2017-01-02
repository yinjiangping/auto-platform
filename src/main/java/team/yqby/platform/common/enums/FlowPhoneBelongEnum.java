package team.yqby.platform.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 手机号归属地标识
 * Author: luwanchuan
 * Date: 2017/1/2
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum FlowPhoneBelongEnum {

    SH_0("0", "上海"),
    NOT_SH_1("1", "非上海");

    private String code;

    private String desc;

}
