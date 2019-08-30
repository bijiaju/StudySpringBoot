package com.bee.springboot.controller.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Consumer {

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();//1.创建一个链接工厂
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();//2.通过连接工厂创建链接

        Channel channel = connection.createChannel();//3.创建channel

        //4.//声明队列（创建）
        String queueName = "test001";

        /**
         * p1 : 队列名
         * p2: 是否持久化到本地
         * p3： 是否独占，用来保证消费队列的顺序性
         * p4:  自动删除，当消息队列和主机没有联系的时候，自动删除队列
         * p5:  拓展参数
         */
        channel.queueDeclare(queueName,true,false,false,null);//

        //5.创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        //6.设置Channel
        channel.basicConsume(queueName,queueingConsumer);
        // 7. 获取消息
        while(true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("消费端：  "+msg);
        }


     /*   //5. 管理链接
        channel.close();
        connection.close();*/

    }
}
