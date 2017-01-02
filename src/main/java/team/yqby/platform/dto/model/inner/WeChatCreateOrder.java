package team.yqby.platform.dto.model.inner;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * </p>
 * User：jumping Date： 2017/1/2 0002 Version：1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("xml")
public class WeChatCreateOrder {
    private String appid;
    private String attach;
    private String body;
    private String mch_id;
    private String detail;
    private String nonce_str;
    private String notify_url;
    private String openid;
    private String out_trade_no;
    private String spbill_create_ip;
    private Long total_fee;
    private String trade_type;
    private String sign;
}
