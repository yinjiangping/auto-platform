package team.yqby.platform.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.yqby.platform.dto.model.FlowBizTrans;
import team.yqby.platform.dto.model.FlowBizTransExample;

public interface FlowBizTransMapper {
    long countByExample(FlowBizTransExample example);

    int deleteByExample(FlowBizTransExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FlowBizTrans record);

    int insertSelective(FlowBizTrans record);

    List<FlowBizTrans> selectByExample(FlowBizTransExample example);

    FlowBizTrans selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FlowBizTrans record, @Param("example") FlowBizTransExample example);

    int updateByExample(@Param("record") FlowBizTrans record, @Param("example") FlowBizTransExample example);

    int updateByPrimaryKeySelective(FlowBizTrans record);

    int updateByPrimaryKey(FlowBizTrans record);
}