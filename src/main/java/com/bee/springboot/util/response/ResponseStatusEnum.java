package com.bee.springboot.util.response;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author weichunhe
 * created at 2018/11/16
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseStatusEnum implements ResponseStatus {
    SUCCESS(true, "成功"),
    UNKNOWN(false, "未知错误"),
    NEED_LOGIN(false, "请登录"),
    USER_NOT_EXISTS(false, "不存在当前用户"),
    USER_PASSWORD_ERROR(false, "密码错误"),
    USER_VERIFYCODE_ERROR(false,"验证码错误"),
    USER_VERIFYCODE_INVALID(false,"验证码失效"),
    USER_VERIFYCODE_LIMIIT(false,"请等待一分钟后获取验证码"),
    OPERATION_IN_ERROR_STATUS(false,"当前状态不允许此操作"),
    FILE_TYPE_ERROR(false,"文件类型不正确"),
    FILE_TOO_BIG(false,"超过文件最大限制");


    private boolean success;

    private String code;

    private String message;

    ResponseStatusEnum(boolean success, String message) {
        this.success = success;
        this.code = this.name();
        this.message = message;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return "ResponseStatusEnum{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
