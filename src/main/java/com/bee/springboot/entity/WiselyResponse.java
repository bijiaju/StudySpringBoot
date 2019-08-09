package com.bee.springboot.entity;

/**
 * 服务器向浏览器响应的信息
 */
public class WiselyResponse {
    private String responseMessage;
    public WiselyResponse(String responseMessage){
        this.responseMessage = responseMessage;
    }
    public String getResponseMessage(){
        return responseMessage;
    }
}