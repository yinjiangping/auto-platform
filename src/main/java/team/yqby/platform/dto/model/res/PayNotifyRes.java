package team.yqby.platform.dto.model.res;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>
 * </p>
 * User：jumping Date： 2017/1/7 0007 Version：1.0
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PayNotifyRes {
    //返回状态码
    private String return_code;
    //返回信息
    private String return_msg;
}
