package team.yqby.platform.mapper;


import team.yqby.platform.dto.model.MchntInfo;

import java.util.List;

/**
 * <p>
 * 用户Mapper
 * </p>
 * User：jumping Date： 2016/10/19 0019 Version：1.0
 */
public interface MchntMapper {

    public MchntInfo findByNo(String mchntName);

    public List<MchntInfo> getAll();

    public int insert(MchntInfo mchntInfo);

}
