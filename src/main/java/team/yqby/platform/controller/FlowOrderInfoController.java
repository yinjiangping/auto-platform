package team.yqby.platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.yqby.platform.common.enums.ArchiveFlagEnum;
import team.yqby.platform.common.enums.ErrorCodeEnum;
import team.yqby.platform.dto.Response;
import team.yqby.platform.dto.model.FlowOrder;
import team.yqby.platform.dto.model.FlowOrderExample;
import team.yqby.platform.mapper.FlowOrderMapper;

import java.util.List;

/**
 * 订单信息查询
 * Author: luwanchuan
 * Date: 2017/1/2
 */
@Slf4j
@Controller
@RequestMapping(value = "/flowOrderInfo")
public class FlowOrderInfoController {

    @Autowired
    private FlowOrderMapper flowOrderMapper;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Response<List<FlowOrder>> queryOrderByOpenIDAndPhone (String openID, String phone, long pageNumber, long pageSize) {
        log.info("查询订单信息，请求参数：用户ID：{}，手机号：{}", openID, phone);
        Response response;
        try {
            /**
            if (StringUtils.isBlank(openID)) {
                response = new Response(ErrorCodeEnum.ILLEGAL_DATA.getCode(), ErrorCodeEnum.ILLEGAL_DATA.getDesc());
                return response;
            }
             */

        /**
            FlowOrderExample example = new FlowOrderExample();
            FlowOrderExample.Criteria criteria = example.createCriteria();
            criteria.andArchiveFlagEqualTo(ArchiveFlagEnum.STR_0.getCode());
            criteria.andOpenIdEqualTo(openID);
            if (StringUtils.isNotBlank(phone)) {
                criteria.andPhoneEqualTo(phone);
            }
            List<FlowOrder> list = flowOrderMapper.selectByExample(example);
         */

            if (pageNumber < 0 || pageSize < 0) {
                response = new Response(ErrorCodeEnum.ILLEGAL_DATA.getCode(), ErrorCodeEnum.ILLEGAL_DATA.getDesc());
                return response;
            }
            long startRow = (pageNumber - 1) * pageSize;
            if (startRow < 1) {
                startRow = 1;
            }
            List<FlowOrder> list = flowOrderMapper.selectBy(openID, phone, startRow, pageSize);
            if (null == list || list.size() < 1) {
                response = new Response(ErrorCodeEnum.DATABASE_SELECT_IS_NULL.getCode(), ErrorCodeEnum.DATABASE_SELECT_IS_NULL.getDesc());
            } else {
                response = new Response(list);
            }

        } catch (Exception e) {
            log.error("查询查询订单信息发生异常：{}", e);
            response = new Response(ErrorCodeEnum.SYSTEM_ERROR.getCode(), ErrorCodeEnum.SYSTEM_ERROR.getDesc());
        }

        log.info("查询查询订单信息，结果为：{}", response);
        return response;
    }

}
