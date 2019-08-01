package com.bee.springboot.util.response;


import java.io.Serializable;

/**
 * Created by byq on 2017/6/14.
 *  通用的结果集
 */


/**
 * CREATE TABLE `tb_app_coophone_info` (
 *   `id` int(11) NOT NULL AUTO_INCREMENT,
 *   `phoneBrand` varchar(255) NOT NULL COMMENT '手机品牌',
 *   `phoneFactory` varchar(255) DEFAULT NULL COMMENT '手机厂商',
 *   `brandModel` varchar(100) NOT NULL COMMENT '品牌型号',
 *   `userNum` varchar(100) DEFAULT NULL COMMENT '用户数',
 *   `chanid` varchar(10) NOT NULL COMMENT '配置方（接入方）',
 *   `updateTime` datetime NOT NULL COMMENT '配置时间',
 *   `updateStaff` varchar(255) NOT NULL COMMENT '配置用户',
 *   `EXT1` varchar(200) DEFAULT NULL COMMENT '扩展',
 *   `EXT2` varchar(200) DEFAULT NULL COMMENT '扩展',
 *   `EXT3` varchar(200) DEFAULT NULL COMMENT '扩展',
 *   PRIMARY KEY (`id`),
 *   UNIQUE KEY `phoneBrand` (`phoneBrand`,`brandModel`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='合作手机信息';
 */

public class Result<T> implements Serializable{

    private static final long serialVersionUID = 1L;

    public static final int OK_CODE = 0;
    public static final int ERROR_CODE = -1;

    private int code;
    private String message;
    private Exception err;

    private T body;//NOSONAR 

    public static Result error(String message) {
        Result result = new Result(-1);
        result.setMessage(message);
        return result;
    }

    public static final Result OK = new Result(0) {
        @Override
        public void setCode(int code) {
            throw new UnsupportedOperationException("can not setCode, please make a new Result");
        }
    };

    public Result() {}

    public Result(int code) {
        this.code = code;
    }

    public boolean hasError() {
        return code != OK_CODE;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getErr() {
        return err;
    }

    public void setErr(Exception err) {
        this.err = err;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
