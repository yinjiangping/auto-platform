package team.yqby.platform.dto.model.req;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import team.yqby.platform.common.util.RegexUtil;
import team.yqby.platform.dto.FlowBaseInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @NotNull(message="手机号不能为空")
    @Pattern(regexp = RegexUtil.product_regex,message = "手机号格式有误")
    private String phone;

    /** 流量编号 */
    @NotNull(message="流量编号不能为空")
    private String flowID;

    /** 现价 */
    @NotNull(message="现价不能为空")
    private Long flowCurrentCost;

    /** 个数 */
    private Long count = 1L;

    /** 请求渠道*/
    private String flowChannel;
}
