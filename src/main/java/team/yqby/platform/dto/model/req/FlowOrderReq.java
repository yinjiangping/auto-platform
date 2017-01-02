package team.yqby.platform.dto.model.req;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import team.yqby.platform.dto.FlowBaseInfo;

import java.util.Date;

/**
 * 订单表
 * Author: luwanchuan
 * Date: 2017/1/1
 */
@ToString(callSuper = true)
@Getter
@Setter
public class FlowOrderReq extends FlowBaseInfo {

    /** 用户编号 */
    private String openID;

    /** 手机号 */
    private String phone;

    /** 流量编号 */
    private Long flowID;

    /** 现价 */
    private Long flowCurrentCost;

    /** 个数 */
    private Long count = 1L;
}
