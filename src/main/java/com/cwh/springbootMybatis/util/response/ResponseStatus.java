package com.cwh.springbootMybatis.util.response;

/**
 * @author weichunhe
 * created at 2018/11/16
 */
public interface ResponseStatus {
    /**
     * is success or not
     *
     * @return true of false
     */
    boolean isSuccess();

    /**
     * provide a program friendly error code when any exception occurred,what always is English
     *
     * @return
     */
    String getCode();

    /**
     * provide a human friendly error message when any exception occurred,what always is 中文
     *
     * @return
     */
    String getMessage();

    /**
     * 判断是否成功
     *
     * @param status
     * @return
     */
    static boolean isSuccess(ResponseStatus status) {
        if (status != null && status.isSuccess()) {
            return true;
        }
        return false;
    }

    /**
     * 生成失败响应
     *
     * @param message
     * @return
     */
    static ResponseStatus fail(String message) {
        DefaultResponseStatus responseStatus = new DefaultResponseStatus(false, "UNSET", message);
        return responseStatus;
    }
    
}