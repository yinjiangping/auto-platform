package team.yqby.platform.dto;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * 基础响应类
 * Author: luwanchuan
 * Date: 2017/1/2
 */
public class Result <T> implements Serializable {

    private static final long serialVersionUID = 6389904919751970160L;

    /**
     * 调用是否成功
     */
    private boolean success;

    /**
     * 调用结果集
     */
    private T result;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误描述
     */
    private String errorMsg;

    /**
     * 用户ID
     */
    private String openID;

    /**
     * 默认构造方法
     */
    public Result() {
    }

    /**
     * 直接构造成功的返回
     * @param result
     */
    public Result(T result) {

        this.success = true;
        this.result = result;
    }

    /**
     * 直接构造失败的返回
     * @param errorCode     错误码
     * @param errorMsg      错误描述
     */
    public Result(String errorCode, String errorMsg) {

        this.success = false;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * 判断调用是否成功
     * @return
     */
    public boolean isSuccess() {

        return success;
    }

    /**
     * 获取调用结果集
     * @return
     */
    public T getResult() {

        return result;
    }

    /**
     * 设置调用结果集
     * @param result    结果集
     */
    public void setResult(T result) {

        success = true;
        this.result = result;
    }

    /**
     * 获取错误码
     * @return
     */
    public String getErrorCode() {

        return errorCode;
    }

    /**
     * 设置错误码
     * @param errorCode     错误码
     */
    public void setErrorCode(String errorCode) {

        this.success = false;
        this.errorCode = errorCode;
    }

    /**
     * 获取错误描述
     * @return
     */
    public String getErrorMsg() {

        return errorMsg;
    }

    /**
     * 设置错误描述
     * @param errorMsg      错误描述
     */
    public void setErrorMsg(String errorMsg) {

        this.errorMsg = errorMsg;
    }

    /**
     * 重写toString方法
     * @return
     */
    @Override
    public String toString() {

        return Objects.toStringHelper(this)
                .add("success", success)
                .add("result", result)
                .add("errorCode", errorCode)
                .add("errorMsg", errorMsg)
                .add("openID", openID)
                .omitNullValues()
                .toString();
    }

}
