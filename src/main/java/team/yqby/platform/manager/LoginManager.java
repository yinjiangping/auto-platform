package team.yqby.platform.manager;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.yqby.platform.common.constant.SystemConstant;
import team.yqby.platform.common.emodel.ServiceErrorCode;
import team.yqby.platform.dto.model.MchntInfo;
import team.yqby.platform.dto.model.UserInfo;
import team.yqby.platform.exception.AutoPlatformException;
import team.yqby.platform.mapper.MchntMapper;
import team.yqby.platform.mapper.UserMapper;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * </p>
 * User：jumping Date： 2016/11/13 0013 Version：1.0
 */
@Service
@Slf4j
public class LoginManager {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MchntMapper mchntMapper;

    /**
     * 查询角色资源信息
     *
     * @return
     */

    public UserInfo checkUser(String userNo, String userPwd, HttpServletRequest request) {

        //校验用户信息
        UserInfo userInfo = checkUser(userNo, userPwd);
        request.getSession().setAttribute(SystemConstant.SESSION_USER, userInfo);
        return userInfo;
    }

    public String createChannelId(String mchntName, String province, String city, String address) {
        ParamManager.checkParam(mchntName,"商户名称");
        ParamManager.checkParam(province,"省份");
        ParamManager.checkParam(city,"城市");
        ParamManager.checkParam(address,"详细地址");
        String channelId = Joiner.on("").join(System.currentTimeMillis(), RandomStringUtils.randomNumeric(5));
        //判断商户信息是否存在
        MchntInfo mchntInfo = mchntMapper.findByNo(mchntName);
        if (mchntInfo != null) {
           return mchntInfo.getChannelId();
        }
        mchntInfo = new MchntInfo();
        mchntInfo.setMchntName(mchntName);
        mchntInfo.setProvince(province);
        mchntInfo.setCity(city);
        mchntInfo.setAddress(address);
        mchntInfo.setChannelId(channelId);
        mchntMapper.insert(mchntInfo);
        return channelId;
    }

    /**
     * 校验用户
     *
     * @param userNo
     * @param userPwd
     */
    public UserInfo checkUser(String userNo, String userPwd) {

        UserInfo userInfo = userMapper.findByNo(userNo);
        //用户名不存在
        if (userInfo == null) {
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10012);
        }
        if (!userInfo.getUserPwd().equals(userPwd)) {
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10013);
        }
        return userInfo;
    }

}
