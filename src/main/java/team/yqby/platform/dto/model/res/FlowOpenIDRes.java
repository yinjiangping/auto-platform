package team.yqby.platform.dto.model.res;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 获取openID的响应参数
 * Author: luwanchuan
 * Date: 2017/1/8
 */
@ToString
@Getter
@Setter
public class FlowOpenIDRes {

    @JSONField(name = "errcode")
    private String errcode;

    @JSONField(name = "errmsg")
    private String errmsg;

    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "expires_in")
    private int expiresIn;

    @JSONField(name = "refresh_token")
    private String refreshToken;

    @JSONField(name = "openid")
    private String openid;

    @JSONField(name = "scope")
    private String scope;

    @JSONField(name = "unionid")
    private String unionid;

}
