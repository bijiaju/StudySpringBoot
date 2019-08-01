package com.bee.springboot.status;

/**
 *
 * @author bee
 * created at 2018/11/22
 */
public enum OperationStatus implements EntityStatus {
    //操作结果,1:成功,2失败
    SUCCEED(1, "成功"),
    FAILED(2,"失败");

    private Integer code;

    private String value;

    OperationStatus(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
