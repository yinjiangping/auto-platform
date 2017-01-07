package team.yqby.platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.yqby.platform.common.enums.ArchiveFlagEnum;
import team.yqby.platform.common.enums.ErrorCodeEnum;
import team.yqby.platform.dto.Response;
import team.yqby.platform.dto.model.FlowStock;
import team.yqby.platform.dto.model.FlowStockExample;
import team.yqby.platform.mapper.FlowStockMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品信息
 * Author: luwanchuan
 * Date: 2017/1/2
 */
@Slf4j
@Controller
@RequestMapping(value = "/flowGoodsInfo")
public class FlowGoodsInfoController {

    @Autowired
    private FlowStockMapper flowStockMapper;

    /**
     * 查询所有有效的商品信息
     * @return
     */
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public Response<List<FlowStock>> queryGoodsInfo() {

        List<FlowStock> list = new ArrayList<>();
        Response response;
        try {
            // 查询有效的商品信息
            FlowStockExample example = new FlowStockExample();
            FlowStockExample.Criteria criteria = example.createCriteria();
            criteria.andArchiveFlagEqualTo(ArchiveFlagEnum.STR_0.getCode());
            list = flowStockMapper.selectByExample(example);

            // 查询不到商品信息
            if (null == list || list.size() < 1) {
                response = new Response(ErrorCodeEnum.DATABASE_SELECT_IS_NULL.getCode(), ErrorCodeEnum.DATABASE_SELECT_IS_NULL.getDesc());
            } else {
                response = new Response(list);
            }
        } catch (Exception e) {
            log.error("查询流量商品信息发生异常：{}", e);
            response = new Response(ErrorCodeEnum.SYSTEM_ERROR.getCode(), ErrorCodeEnum.SYSTEM_ERROR.getDesc());
        }

        log.info("查询流量商品信息，结果为：{}", response);
        return response;
    }

}
