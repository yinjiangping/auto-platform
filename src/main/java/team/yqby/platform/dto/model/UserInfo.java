package team.yqby.platform.dto.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 *     用户基本信息
 * </p>
 * User：jumping Date： 2016/11/8 0008 Version：1.0
 */
@ToString(callSuper = true)
@Getter
@Setter
public class UserInfo  {
    private int id;
    /**
     * 用户登陆账户
     */
    private String userNo;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 角色编号
     */
    private int roleId;

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 用户密码
     */
    private String userPwd;
    /**
     * 用户手机
     */
    private String userMobile;

    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新日期
     */
    private Date updateDate;

    /**
     * 归属
     */
    private String belong;
    /**
     * 归档人
     */
    private String archiveBy;
    /**
     * 归档日期
     */
    private Date archiveDate;
    /**
     * 归档标识
     */
    private String archiveFlag;
}
