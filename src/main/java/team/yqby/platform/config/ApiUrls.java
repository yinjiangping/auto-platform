package team.yqby.platform.config;

/**
 * 接口地址
 * @author jumping
 * @date 2015/11/16
 */
public class ApiUrls {

    /**    创建session  **/
    public static final String CREATE_SESSION_ID_URL="/service/service/authority/query/getUserStar.do";

    /**    生成验证码  **/
    public static final String CREATE_CODE_URL="/service/createValidate.do";

    /**    校验验证码  **/
    public static final String VALIDATE_CODE_URL="/service/validateCode.do";

    /**    查询账单  **/
    public static final String QUERY_BILL_URL="/service/insteadPayBill.do";

    /**  查询余额 **/
    public static final String QUERY_ACCOUNT_BALANCE="/service/my/queryCardBalance";

    /**    创建订单  **/
    public static final String CREATE_ORDER_URL="/service/billRecharge/doPay.do";

    /**    支付订单  **/
    public static final String ORDER_PAY_URL="/service/billRecharge/doSelect.jhtml";

    //创建流量订单
    public static final String FLOW_CREATE_ORDER_URL="/order";

    //支付回调通知
    public static final String FLOW_PAY_NOTIFY_URL="/payCallBack";

    //业务回调通知
    public static final String FLOW_BIZ_NOTIFY_URL="/bizBack";

    //获取授权信息
    public static final String FLOW_GET_GRANT_URL="/getGrant";

}
