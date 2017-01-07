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
import team.yqby.platform.common.enums.ErrorCodeEnum;
import team.yqby.platform.common.enums.FlowPhoneBelongEnum;
import team.yqby.platform.dto.PhoneBelongDto;
import team.yqby.platform.dto.Response;
import team.yqby.platform.dto.model.FlowPhoneBin;
import team.yqby.platform.dto.model.FlowPhoneBinExample;
import team.yqby.platform.mapper.FlowPhoneBinMapper;

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
    private FlowPhoneBinMapper flowPhoneBinMapper;

    @RequestMapping(value = "/queryBin", method = RequestMethod.POST)
    @ResponseBody
    public Response<PhoneBelongDto> queryPhoneBinByPhone(String openID, String phone) {
        log.info("查询手机号归属地，请求参数：用户ID：{}，手机号：{}", openID, phone);

        Response response;

        try {
            if (StringUtils.isBlank(phone) || phone.trim().length() < 7) {
                response = new Response(ErrorCodeEnum.ILLEGAL_DATA.getCode(), ErrorCodeEnum.ILLEGAL_DATA.getDesc());
                return response;
            }

            FlowPhoneBinExample example = new FlowPhoneBinExample();
            FlowPhoneBinExample.Criteria criteria = example.createCriteria();
            criteria.andArchiveFlagEqualTo(ArchiveFlagEnum.STR_0.getCode());
            criteria.andPhoneBinEqualTo(phone.trim().substring(0, 6));

            List<FlowPhoneBin> list = flowPhoneBinMapper.selectByExample(example);
            PhoneBelongDto phoneBelongDto = new PhoneBelongDto();
            // 查询不到归属地信息
            if (null == list || list.size() < 1) {
                // 非上海号码
                phoneBelongDto.setPhoneBelong(FlowPhoneBelongEnum.NOT_SH_1.getCode());
            } else {
                //上海号码
                phoneBelongDto.setPhoneBelong(FlowConstant.CITY_CODE_021.equals(list.get(0).getCityCode()) ? FlowPhoneBelongEnum.SH_0.getCode() : FlowPhoneBelongEnum.NOT_SH_1.getCode());
            }
            response = new Response(phoneBelongDto);

        } catch (Exception e) {
            log.error("查询手机号归属地发生异常：{}", e);
            response = new Response(ErrorCodeEnum.SYSTEM_ERROR.getCode(), ErrorCodeEnum.SYSTEM_ERROR.getDesc());
        }

        log.info("查询手机号归属地，结果为：{}", response);
        return response;
    }

}
