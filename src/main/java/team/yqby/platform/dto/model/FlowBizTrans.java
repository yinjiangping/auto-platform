package team.yqby.platform.dto.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import team.yqby.platform.dto.FlowBaseInfo;

import java.util.Date;

/**
 * 业务流水表
 * Author: luwanchuan
 * Date: 2017/1/1
 */
@ToString(callSuper = true)
@Getter
@Setter
public class FlowBizTrans extends FlowBaseInfo {

    /** ID自增 */
    private Long id;

    /** 订单号 */
    private String orderId;

    /** 业务请求流水号 */
    private String bizId;

    /** 流量编号ID */
    private Long flowId;

    /** 外部流量编号 */
    private String outterFlowId;

    /** 支付价 */
    private Long currentCost;

    /** 手机号，被充值对象 */
    private String phone;

    /** 业务流水状态 */
    private String transStatus;

    /** 业务请求时间 */
    private Date bizReqTime;

    /** 业务返回时间 */
    private Date bizRespTime;

    /** 业务响应流水号 */
    private String bizRespId;

    /** 业务返回码 */
    private String bizRespCode;

    /** 业务返回描述 */
    private String bizRespDesc;

    /** 对账状态：0：初始化，1：对账成功 */
    private String checkStatus;

}
