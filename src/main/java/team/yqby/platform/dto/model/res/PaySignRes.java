package team.yqby.platform.dto.model.res;

import lombok.*;

/**
 * <p>
 * </p>
 * User：jumping Date： 2017/1/8 0008 Version：1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaySignRes {
    private String appId;
    private Long timeStamp;
    private String nonceStr;
    private String signature;
    private String url;
}
