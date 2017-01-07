package team.yqby.platform.dto.model.req;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import team.yqby.platform.common.util.XStreamUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * <p>
 * 微信支付结果通知请求对象
 * </p>
 * User：jumping Date： 2017/1/7 0007 Version：1.0
 */
@Getter
@Setter
@ToString
//@XStreamAlias("xml")
public class PayNotifyReq {

    public static XStream xstream = new XStream();

    static {
        xstream.alias("xml", PayNotifyReq.class);
        XStreamUtil.aliasAttributeUseUpperCase(PayNotifyReq.class, xstream, false);
    }

    //公众账号ID
    private String appid;
    //商家数据包
    private String attach;
    //付款银行
    private String bank_type;
    //货币种类
    private String fee_type;
    //是否关注公众账号
    private String is_subscribe;
    //商户号
    private String mch_id;
    //随机字符串
    private String nonce_str;
    //用户标识
    private String openid;
    //商户订单号
    private String out_trade_no;
    //业务结果
    private String result_code;
    //错误码
    private String err_code;
    //错误描述
    private String err_code_des;
    //返回状态码
    private String return_code;
    //返回消息
    private String return_msg;
    //签名
    private String sign;
    //子商户号
    private String sub_mch_id;
    //支付完成时间
    private String time_end;
    //订单金额
    private String total_fee;
    //交易类型
    private String trade_type;
    //微信支付订单号
    private String transaction_id;

    public static PayNotifyReq fromXML(String xml) {
        return XStreamUtil.fromXML(xml, xstream);
    }
}
