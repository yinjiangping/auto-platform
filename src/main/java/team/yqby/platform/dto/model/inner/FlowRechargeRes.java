package team.yqby.platform.dto.model.inner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *      流量充值返回
 * </p>
 * User：jumping Date： 2017/1/7 0007 Version：1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlowRechargeRes {
    private String ret;
    private String flowrecord;
    private String msg;
}
