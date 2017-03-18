package team.yqby.platform.mapper;

import org.apache.ibatis.annotations.Param;
import team.yqby.platform.dto.model.UserInfo;

import java.util.List;

/**
 * <p>
 * 用户Mapper
 * </p>
 * User：jumping Date： 2016/10/19 0019 Version：1.0
 */
public interface UserMapper {

    public UserInfo findByUser(String userName, String userPwd);

    public UserInfo findByNo(String dtdNo);

    public List<UserInfo> getAll(@Param("userName") String userName);

    public int insert(UserInfo departInfo);

    public int update(UserInfo departInfo);

    public int delete(int dtdNo);
}
