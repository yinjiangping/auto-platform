package team.yqby.platform.dto.model;

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
public class FlowOrder extends FlowBaseInfo {

    /** ID自增 */
    private Long id;

    /** 订单号 */
    private String orderId;

    /** 订单时间 */
    private Date orderTime;

    /** 流量编号ID */
    private Long flowId;

    /** 外部流量编号 */
    private String outterFlowId;

    /** 原价 */
    private Long originalCost;

    /** 支付价 */
    private Long currentCost;

    /** 手机号，被充值对象 */
    private String phone;

    /** 用户编号 */
    private String openId;

    /** 订单状态 */
    private String transStatus;

    /** 支付请求时间 */
    private Date payReqTime;

    /** 支付返回时间 */
    private Date payRespTime;

    /** 支付返回码 */
    private String payRespCode;

    /** 支付返回描述 */
    private String payRespDesc;

    /** 对账状态：0：初始化，1：对账成功 */
    private String checkStatus;

}
