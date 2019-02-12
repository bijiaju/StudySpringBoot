package com.zx.sms.common.bean;

import java.io.Serializable;

/**
 * @author weichunhe
 * created at 2018/11/18
 */
public class DefaultResponseStatus implements ResponseStatus , Serializable {
    private static final long serialVersionUID = -7428056103897199083L;
    private boolean success;

    private String code;

    private String message;

    public static ResponseStatus fail(String code, String message) {
        DefaultResponseStatus responseStatus = new DefaultResponseStatus(false, code, message);
        return responseStatus;
    }

    public DefaultResponseStatus(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DefaultResponseStatus{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
