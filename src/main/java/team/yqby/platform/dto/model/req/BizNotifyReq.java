package team.yqby.platform.dto.model.req;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * </p>
 * User：jumping Date： 2017/1/8 0008 Version：1.0
 */
@ToString(callSuper = true)
@Getter
@Setter
public class BizNotifyReq {
    //订单号(订购返回的订单号)
    @NotNull(message="订单号不能为空")
    private String flowrecord;
    //平台分配的渠道id
    @NotNull(message="平台分配的渠道id不能为空")
    private String channelid;
    //时间戳字符串
    @NotNull(message="时间戳字符串不能为空")
    private String timestamp;
    //000：成功  001：失败
    @NotNull(message="结果不能为空")
    private String result;
    //签名
    @NotNull(message="签名不能为空")
    private String sign;
    //渠道上送订单号
    @NotNull(message="渠道订单号不能为空")
    private String channelorderid;
}
