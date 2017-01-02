package team.yqby.platform.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.yqby.platform.dto.model.FlowPhoneBin;
import team.yqby.platform.dto.model.FlowPhoneBinExample;

public interface FlowPhoneBinMapper {
    long countByExample(FlowPhoneBinExample example);

    int deleteByExample(FlowPhoneBinExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FlowPhoneBin record);

    int insertSelective(FlowPhoneBin record);

    List<FlowPhoneBin> selectByExample(FlowPhoneBinExample example);

    FlowPhoneBin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FlowPhoneBin record, @Param("example") FlowPhoneBinExample example);

    int updateByExample(@Param("record") FlowPhoneBin record, @Param("example") FlowPhoneBinExample example);

    int updateByPrimaryKeySelective(FlowPhoneBin record);

    int updateByPrimaryKey(FlowPhoneBin record);
}