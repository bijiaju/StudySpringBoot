package com.bee.springboot.controller.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * 这个可以使用
 */
public class Producer {

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();//1.创建一个链接工厂
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //设置虚拟机，一个mq服务可以设置多个虚拟机，每个虚拟机相当于一个独立的mq
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();//2.通过连接工厂创建链接

        Channel channel = connection.createChannel();//3.创建channel

        /**
         * p1 : 队列名
         * p2: 是否持久化到本地
         * p3： 是否独占，用来保证消费队列的顺序性
         * p4:  自动删除，当消息队列和主机没有联系的时候，自动删除队列
         * p5:  拓展参数
         */
        channel.queueDeclare("test001",true,false,false,null);//

        //4.发送消息
        byte[] msg = "hello Rabbit".getBytes();
        for(int i=0;i<5;i++){
            channel.basicPublish("","test001",null,msg);// exchanage 暂时为“”,默认为空走第一个amqp，按照routingkey来转发队列；routekey为“", 后两个参数为消息内容（property为消息的属性），msg为实体内容
        }

        //5. 管理链接
        channel.close();
        connection.close();


    }
}
