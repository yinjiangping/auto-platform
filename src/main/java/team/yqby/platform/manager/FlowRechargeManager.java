package team.yqby.platform.manager;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.yqby.platform.common.emodel.ServiceErrorCode;
import team.yqby.platform.dto.FlowBaseInfo;
import team.yqby.platform.dto.model.FlowOrder;
import team.yqby.platform.dto.model.FlowStock;
import team.yqby.platform.dto.model.FlowStockExample;
import team.yqby.platform.exception.AutoPlatformException;
import team.yqby.platform.mapper.FlowOrderMapper;
import team.yqby.platform.mapper.FlowStockMapper;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * </p>
 * User：jumping Date： 2017/1/2 0002 Version：1.0
 */
@Service
@Slf4j
public class FlowRechargeManager {
    @Autowired
    private FlowStockMapper flowStockMapper;
    @Autowired
    private FlowOrderMapper flowOrderMapper;

    /**
     * 检查商品价格
     */
    public FlowStock checkGoodsPrice(String flowID, Long flowCurrentCost) {
        //1.根据流量编号查询价格
        FlowStockExample flowStockExample = new FlowStockExample();
        flowStockExample.createCriteria().andFlowIdEqualTo(flowID).andArchiveFlagEqualTo("0");
        List<FlowStock> flowStocks = flowStockMapper.selectByExample(flowStockExample);
        if (flowStocks == null || flowStocks.size() == 0) {
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10001);
        }
        //2.判断商户价格是否一致
        FlowStock flowStock = flowStocks.get(0);
        Long currentCost = flowStock.getFlowCurrentCost();
        if (flowCurrentCost.longValue() != currentCost.longValue()) {
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10002);
        }
        return flowStock;
    }

    /***
     * 支付下单
     * @param flowStock  商品信息
     * @param productNo  手机号
     * @param openID        用户编号
     * @return
     */
    public String createPayOrder(FlowStock flowStock, String productNo, String openID) {
        FlowOrder flowOrder = new FlowOrder();
        flowOrder.setOrderId(DateUtils.formatDate(new Date(), "YYMMDDHHMMSSs" + (int) ((Math.random() * 9 + 1) * 100)));
        flowOrder.setOrderTime(DateUtils.parseDate(DateUtils.formatDate(new Date(), "YYMMDDHHMMSS")));
        flowOrder.setFlowId(Long.valueOf(flowStock.getFlowId()));
        flowOrder.setOriginalCost(flowStock.getFlowOriginalCost());
        flowOrder.setCurrentCost(flowStock.getFlowCurrentCost());
        flowOrder.setPhone(productNo);
        flowOrder.setOpenId(openID);
        flowOrder.setTransStatus("INI");
        flowOrder.setPayReqTime(new Date());
        flowOrder.setCheckStatus("0");
        flowOrder.setArchiveFlag("0");
        flowOrder.setCreateBy(openID);
        flowOrder.setCreateDate(new Date());
        int i = flowOrderMapper.insert(flowOrder);
        if (i == 0) {
            throw new AutoPlatformException(ServiceErrorCode.ERROR_CODE_A10003);
        }
        return flowOrder.getOrderId();
    }

}
