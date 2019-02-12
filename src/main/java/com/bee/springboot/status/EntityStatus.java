package com.zx.sms.common.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 实体类状态
 * @author weichunhe
 * created at 2018/11/22
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface EntityStatus {
    /**
     * 获取状态编码
     * @return
     */
    Integer getCode();

    /**
     * 获取状态说明
     * @return
     */
    String getValue();
}
