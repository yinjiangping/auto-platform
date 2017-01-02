package team.yqby.platform.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 异常信息
 * Author: luwanchuan
 * Date: 2017/1/2
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCodeEnum {

    DATABASE_SELECT_IS_NULL("DATABASE_SELECT_IS_NULL", "数据库查询结果为空"),
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常");

    private String code;

    private String desc;

}
