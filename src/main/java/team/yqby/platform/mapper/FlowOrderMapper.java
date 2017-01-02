package team.yqby.platform.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.yqby.platform.dto.model.FlowOrder;
import team.yqby.platform.dto.model.FlowOrderExample;

public interface FlowOrderMapper {
    long countByExample(FlowOrderExample example);

    int deleteByExample(FlowOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FlowOrder record);

    int insertSelective(FlowOrder record);

    List<FlowOrder> selectByExample(FlowOrderExample example);

    FlowOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FlowOrder record, @Param("example") FlowOrderExample example);

    int updateByExample(@Param("record") FlowOrder record, @Param("example") FlowOrderExample example);

    int updateByPrimaryKeySelective(FlowOrder record);

    int updateByPrimaryKey(FlowOrder record);
}