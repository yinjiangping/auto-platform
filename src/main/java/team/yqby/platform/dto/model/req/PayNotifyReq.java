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
    //商户号
    private String mch_id;
    //设备号
    private String device_info;
    //随机字符串
    private String nonce_str;
    //签名
    private String sign;
    //签名类型
    private String sign_type;
    //业务结果
    private String result_code;
    //错误码
    private String err_code;
    //错误描述
    private String err_code_des;
    //用户标识
    private String openid;
    //是否关注公众号
    private String is_subscribe;
    //付款银行
    private String bank_type;
    //交易类型
    private String trade_type;
    //订单金额
    private String total_fee;
    //应结金额
    private String settlement_total_fee;
    //货币种类
    private String fee_type;
    //现金支付金额
    private String cash_fee;
    //现金支付币种
    private String cash_fee_type;
    //总代金券金额
    private String coupon_fee;
    //代金券使用数量
    private String coupon_count;
    //代金券类型
    private String coupon_type_0;
    //代金券ID
    private String coupon_id_0;
    //单个代金券支付金额
    private String coupon_fee_0;
    //微信支付订单号
    private String transaction_id;
    //商户订单号
    private String out_trade_no;
    //商家数据包
    private String attach;
    //返回状态码
    private String return_code;
    //返回消息
    private String return_msg;
    //子商户号
    private String sub_mch_id;
    //支付完成时间
    private String time_end;


    public static PayNotifyReq fromXML(String xml) {
        return XStreamUtil.fromXML(xml, xstream);
    }
}
