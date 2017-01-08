package team.yqby.platform.dto.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单信息查询
 * Author: luwanchuan
 * Date: 2017/1/7
 */
@ToString
@Getter
@Setter
public class FlowOrderQueryDto implements Serializable {

    private static final long serialVersionUID = 7255753370931859991L;

    /** 订单号 */
    private String orderId;

    /** 订单时间 */
    private Date orderTime;

    /** 支付价 */
    private Long currentCost;

    /** 手机号，被充值对象 */
    private String phone;

    /** 订单状态 */
    private String transStatus;

    /** 流量名称 */
    private String flowTitle;

    /** 流量描述 */
    private String flowDesc;

}
