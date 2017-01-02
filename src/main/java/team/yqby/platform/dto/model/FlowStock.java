package team.yqby.platform.dto.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import team.yqby.platform.dto.FlowBaseInfo;

/**
 * 流量库存表
 * Author: luwanchuan
 * Date: 2017/1/1
 */
@ToString(callSuper = true)
@Getter
@Setter
public class FlowStock extends FlowBaseInfo {

    /** ID自增 */
    private Long id;

    /** 库存流量编号 */
    private String flowId;

    /** 流量归属地：SH_LOCAL：上海本地，SH_ALL：上海全国，ALL：全国 */
    private String flowBelong;

    /** 流量名称 */
    private String flowTitle;

    /** 流量描述 */
    private String flowDesc;

    /** 流量原价 */
    private Long flowOriginalCost;

    /** 流量现价 */
    private Long flowCurrentCost;

    /** 库存 */
    private Long stock;

    /** 外部流量编号 */
    private String outterFlowId;

}
