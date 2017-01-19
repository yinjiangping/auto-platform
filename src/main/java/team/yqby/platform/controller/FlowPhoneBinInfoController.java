package team.yqby.platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.yqby.platform.common.constant.FlowConstant;
import team.yqby.platform.common.enums.ArchiveFlagEnum;
import team.yqby.platform.common.enums.CarrierNameEnum;
import team.yqby.platform.common.enums.ErrorCodeEnum;
import team.yqby.platform.common.enums.FlowPhoneBelongEnum;
import team.yqby.platform.config.PublicConfig;
import team.yqby.platform.dto.model.res.PayNotifyRes;
import team.yqby.platform.dto.query.PhoneBelongDto;
import team.yqby.platform.dto.Response;
import team.yqby.platform.dto.model.FlowPhoneBin;
import team.yqby.platform.dto.model.FlowPhoneBinExample;
import team.yqby.platform.exception.AutoPlatformException;
import team.yqby.platform.mapper.FlowPhoneBinMapper;
import team.yqby.platform.service.FlowPhoneBinService;

import java.util.List;

/**
 * 手机号归属地
 * Author: luwanchuan
 * Date: 2017/1/2
 */
@Slf4j
@Controller
public class FlowPhoneBinInfoController {

    @Autowired
    private FlowPhoneBinService flowPhoneBinService;

    @RequestMapping(value = "/queryBin", method = RequestMethod.POST)
    @ResponseBody
    public Response<PhoneBelongDto> queryPhoneBinByPhone(String openID, String phone) {
        log.info("查询手机号归属地，请求参数：用户ID：{}，手机号：{}", openID, phone);

        Response response;

        try {
            //查询手机归属地
            response = new Response(flowPhoneBinService.getPhoneCarrier(phone));

        } catch (AutoPlatformException e) {
            log.error("查询手机号归属地发生异常：", e);
            return new Response(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("查询手机号归属地发生异常：", e);
            response = new Response(ErrorCodeEnum.SYSTEM_ERROR);
        }

        log.info("查询手机号归属地，结果为：{}", response);
        return response;
    }

}
