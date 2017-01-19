package team.yqby.platform.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.yqby.platform.common.constant.FlowConstant;
import team.yqby.platform.common.enums.ArchiveFlagEnum;
import team.yqby.platform.common.enums.CarrierNameEnum;
import team.yqby.platform.common.enums.ErrorCodeEnum;
import team.yqby.platform.common.enums.FlowPhoneBelongEnum;
import team.yqby.platform.dto.Response;
import team.yqby.platform.dto.model.FlowPhoneBin;
import team.yqby.platform.dto.model.FlowPhoneBinExample;
import team.yqby.platform.dto.query.PhoneBelongDto;
import team.yqby.platform.exception.AutoPlatformException;
import team.yqby.platform.mapper.FlowPhoneBinMapper;

import java.util.List;

/**
 * <p>
 * </p>
 * User：jumping Date： 2017/1/17 0017 Version：1.0
 */
@Service
@Slf4j
public class FlowPhoneBinService {
    @Autowired
    private FlowPhoneBinMapper flowPhoneBinMapper;

    /**
     * 查询手机归属地
     *
     * @param phone 查询手机归属地
     * @return
     */
    public PhoneBelongDto getPhoneCarrier(String phone) {

        if (StringUtils.isBlank(phone) || phone.trim().length() < 7) {
            throw new AutoPlatformException(ErrorCodeEnum.ILLEGAL_DATA.getCode(), ErrorCodeEnum.ILLEGAL_DATA.getCode());
        }
        FlowPhoneBinExample example = new FlowPhoneBinExample();
        FlowPhoneBinExample.Criteria criteria = example.createCriteria();
        criteria.andArchiveFlagEqualTo(ArchiveFlagEnum.STR_0.getCode());
        criteria.andPhoneBinEqualTo(phone.trim().substring(0, 7));

        List<FlowPhoneBin> list = flowPhoneBinMapper.selectByExample(example);
        PhoneBelongDto phoneBelongDto = new PhoneBelongDto();
        // 查询不到归属地信息
        if (null == list || list.size() < 1) {
            // 非上海号码
            phoneBelongDto.setPhoneBelong(FlowPhoneBelongEnum.NOT_SH_1.getCode());
        } else {
            //上海号码
            phoneBelongDto.setPhoneBelong(
                    (CarrierNameEnum.DX.getCode().equals(list.get(0).getCarrierName()) && FlowConstant.CITY_CODE_021.equals(list.get(0).getCityCode()))
                            ? FlowPhoneBelongEnum.SH_0.getCode() : FlowPhoneBelongEnum.NOT_SH_1.getCode());
            phoneBelongDto.setCarrierName(list.get(0).getCarrierName());
        }
        return phoneBelongDto;
    }
}
