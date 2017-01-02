package team.yqby.platform.dto.model.res;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * </p>
 * User：jumping Date： 2017/1/2 0002 Version：1.0
 */
@ToString(callSuper = true)
@Getter
@Setter
public class FlowOrderRes {
    //订单号
    private String orderNo;
    //订单金额
    private Long flowCurrentCost;
}
