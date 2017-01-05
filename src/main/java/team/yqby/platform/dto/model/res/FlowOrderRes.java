package team.yqby.platform.dto.model.res;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * </p>
 * User：jumping Date： 2017/1/2 0002 Version：1.0
 */
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class FlowOrderRes {
    //公众号id
    private String appId;
    //时间戳
    private Long timeStamp;
    //随机字符串
    private String nonceStr;
    //订单详情扩展字符串
    @JSONField(name = "package")
    private String pack_age;
    //签名方式
    private String signType;
    //签名
    private String paySign;
}
