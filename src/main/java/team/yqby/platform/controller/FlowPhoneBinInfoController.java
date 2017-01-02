package team.yqby.platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import team.yqby.platform.common.constant.FlowConstant;
import team.yqby.platform.common.enums.ArchiveFlagEnum;
import team.yqby.platform.common.enums.ErrorCodeEnum;
import team.yqby.platform.common.enums.FlowPhoneBelongEnum;
import team.yqby.platform.dto.PhoneBelongDto;
import team.yqby.platform.dto.Result;
import team.yqby.platform.dto.model.FlowPhoneBin;
import team.yqby.platform.dto.model.FlowPhoneBinExample;
import team.yqby.platform.dto.model.FlowStockExample;
import team.yqby.platform.mapper.FlowPhoneBinMapper;

import java.util.List;

/**
 * 手机号归属地
 * Author: luwanchuan
 * Date: 2017/1/2
 */
@Slf4j
@Controller
@RequestMapping(value = "flowPhoneBinInfo")
public class FlowPhoneBinInfoController {

    @Autowired
    private FlowPhoneBinMapper flowPhoneBinMapper;

    @RequestMapping(value = "queryPhoneBinByNo")
    public String queryPhoneBinByNo(String openID, String phoneNo) {
        log.info("查询手机号归属地，请求参数：用户ID：{}，手机号：{}", openID, phoneNo);
        Result result = new Result();

        try {
            if (StringUtils.isBlank(phoneNo) || phoneNo.trim().length() < 7) {
                result.setErrorCode(ErrorCodeEnum.ILLEGAL_DATA.getCode());
                result.setErrorMsg(ErrorCodeEnum.ILLEGAL_DATA.getDesc());
                // TODO
            }

            FlowPhoneBinExample example = new FlowPhoneBinExample();
            FlowPhoneBinExample.Criteria criteria = example.createCriteria();
            criteria.andArchiveFlagEqualTo(ArchiveFlagEnum.STR_0.getCode());
            criteria.andPhoneBinEqualTo(phoneNo.trim().substring(0, 6));

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
            result.setResult(phoneBelongDto);

        } catch (Exception e) {
            log.error("查询手机号归属地发生异常：{}", e);
            result.setErrorCode(ErrorCodeEnum.SYSTEM_ERROR.getCode());
            result.setErrorMsg(ErrorCodeEnum.SYSTEM_ERROR.getDesc());
        }

        log.info("查询手机号归属地，结果为：{}", result);
        return null;
    }

}
