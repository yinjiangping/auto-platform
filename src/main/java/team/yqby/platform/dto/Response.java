package team.yqby.platform.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 通用返回值
 * </p>
 * User：jumping Date： 2017/1/2 0002 Version：1.0
 */
@ToString
@Getter
@Setter
public class Response<T> {
    private String errorCode;
    private String errorMsg;
    private boolean success;
    private T result;

    public Response(String errorCode, String errorMsg) {
        this.success = false;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Response(T result) {
        this.success = true;
        this.result = result;
    }
}
