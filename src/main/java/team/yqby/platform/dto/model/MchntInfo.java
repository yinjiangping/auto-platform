package team.yqby.platform.dto.model;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * </p>
 * User：jumping Date： 2017/3/16 0016 Version：1.0
 */
@Getter
@Setter
public class MchntInfo {
    private int id;
    private String mchntName;
    private String province;
    private String city;
    private String address;
    private String channelId;
}
