package team.yqby.platform.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.yqby.platform.dto.model.FlowStock;
import team.yqby.platform.dto.model.FlowStockExample;

public interface FlowStockMapper {
    long countByExample(FlowStockExample example);

    int deleteByExample(FlowStockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FlowStock record);

    int insertSelective(FlowStock record);

    List<FlowStock> selectByExample(FlowStockExample example);

    FlowStock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FlowStock record, @Param("example") FlowStockExample example);

    int updateByExample(@Param("record") FlowStock record, @Param("example") FlowStockExample example);

    int updateByPrimaryKeySelective(FlowStock record);

    int updateByPrimaryKey(FlowStock record);
}