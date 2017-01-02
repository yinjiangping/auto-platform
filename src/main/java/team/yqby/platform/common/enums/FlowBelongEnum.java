package team.yqby.platform.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 流量归属地
 * Author: luwanchuan
 * Date: 2017/1/2
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum FlowBelongEnum {

    SH_LOCAL("SH_LOCAL", "上海电信本地"),
    SH_ALL("SH_ALL", "上海电信全国"),
    ALL("ALL", "全国电信");

    private String code;

    private String desc;

}
