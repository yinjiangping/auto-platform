package team.yqby.platform.dto.query;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Author: luwanchuan
 * Date: 2017/1/8
 */
@ToString
@Getter
@Setter
public class FlowOpenIDDto implements Serializable {

    private static final long serialVersionUID = 7941220567026640733L;

    private String openID;

}
