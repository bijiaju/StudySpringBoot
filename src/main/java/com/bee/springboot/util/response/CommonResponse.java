package com.bee.springboot.util.response;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;

/**
 * the common response bean
 *
 * @author weichunhe
 */
public class CommonResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    public static CommonResponse successResponse(Object data) {
        return new CommonResponse(ResponseStatusEnum.SUCCESS, data);
    }

    public static CommonResponse successPage(Object data) {
        if (data instanceof Page) {
            return new CommonResponse(ResponseStatusEnum.SUCCESS, new PageInfo((Page) data));
        }
        return CommonResponse.failResponse(ResponseStatus.fail("当前数据无法生成分页信息"));
    }

    public static CommonResponse failResponse(ResponseStatus status) {
        return new CommonResponse(status, null);
    }

    private CommonResponse() {

    }

    private CommonResponse(ResponseStatus status, Object data) {
        this.status = status;
        this.data = data;
    }

    private ResponseStatus status;

    private Object data;

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    /**
     * data of response
     *
     * @return data or null when failed
     */
    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
