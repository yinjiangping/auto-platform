package team.yqby.platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import team.yqby.platform.dto.model.UserInfo;
import team.yqby.platform.exception.AutoPlatformException;
import team.yqby.platform.manager.LoginManager;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class LoginController {
    @Autowired
    private LoginManager loginManager;

    /**
     * 用户登陆
     *
     * @param userName 用户名
     * @param userPwd  用户密码
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView login(String userName, String userPwd, HttpServletRequest request) {
        UserInfo userInfo = null;
        try {
            userInfo = loginManager.checkUser(userName, userPwd, request);
        } catch (AutoPlatformException e) {
            log.error("AutoPlatformException error,", e);
            return new ModelAndView("login").addObject("errorMsg", e.getMessage());
        }
        return new ModelAndView("index").addObject("loginResult", userInfo);
    }

    @RequestMapping(value = "/createChannelId")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String createChannelId(String mchntName, String province, String city, String address, HttpServletRequest request) {
        String channelId = "";
        try {
            channelId = loginManager.createChannelId(mchntName, province, city, address);
        } catch (Exception e) {
            log.error("Exception error,", e);
            return "valid param error";
        }
        log.info("商户{}获取渠道ID成功,channelId:{}", mchntName, channelId);
        return channelId;
    }

}
