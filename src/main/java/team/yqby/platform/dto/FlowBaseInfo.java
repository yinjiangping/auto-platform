package team.yqby.platform.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础信息
 * Author: luwanchuan
 * Date: 2017/1/1
 */
@ToString
@Getter
@Setter
public class FlowBaseInfo implements Serializable {

    private static final long serialVersionUID = 6449597046264174703L;

    /** 修改人 */
    private String updateBy;

    /** 修改时间 */
    private Date updateDate;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private Date createDate;

    /** 归档标识：0：有效，1：无效 */
    private String archiveFlag;

}
