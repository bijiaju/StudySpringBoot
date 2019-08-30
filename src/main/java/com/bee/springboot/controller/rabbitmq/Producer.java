package com.bee.springboot.controller.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();//1.创建一个链接工厂
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();//2.通过连接工厂创建链接

        Channel channel = connection.createChannel();//3.创建channel

        //4.发送消息
        byte[] msg = "hello Rabbit".getBytes();
        for(int i=0;i<5;i++){
            channel.basicPublish("","test001",null,msg);// exchanage 暂时为“”,默认为空走第一个amqp，按照routingkey来找队列；routekey为“", 后两个参数为消息内容（property为消息的属性），msg为实体内容
        }

        //5. 管理链接
        channel.close();
        connection.close();


    }
}
