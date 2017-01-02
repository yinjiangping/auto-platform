package team.yqby.platform.dto.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import team.yqby.platform.dto.FlowBaseInfo;

/**
 * 手机号归属地表
 * Author: luwanchuan
 * Date: 2017/1/1
 */
@ToString(callSuper = true)
@Getter
@Setter
public class FlowPhoneBin extends FlowBaseInfo {

    /** ID自增 */
    private Long id;

    /** 手机号前7位 */
    private String phoneBin;

    /** 省份编码 */
    private String provenceCode;

    /** 城市编码 */
    private String cityCode;

    /** 城市名 */
    private String cityName;

    /** 手机归属服务商：dx：电信，yd：移动，lt：联通 */
    private String carrierName;

}
