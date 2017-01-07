package team.yqby.platform.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 归档标识
 * Author: luwanchuan
 * Date: 2017/1/2
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum CheckStatusEnum {
    //对账初始化
    STR_0("0"),
    //对账成功
    STR_1("1");

    private String code;

}
