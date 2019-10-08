package com.bee.springboot.controller.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 发布订阅模式
 * 1.声明   2个以上队列和一个交换机，绑定exchange，routingkey不用指定
 */
public class Producer_publicsh {

    private static final String QUEUE_INFORM_EMAIL= "QUEUE_INFORM_EMAIL";//
    private static final String QUEUE_INFORM_SMS= "QUEUE_INFORM_SMS";//
    private static final String EXCHANGE_FANOUT_INFORM= "EXCHANGE_FANOUT_INFORM";//

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

         //声明交换机 String exchange, BuiltinExchangeType type 和队列
                  /**
                      * 参数明细
                      * 1、交换机名称
                      * 2、交换机类型，fanout、topic、direct、headers
                      */
                  //channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM,BuiltinExchangeType.FANOUT);
                  channel.queueDeclare(QUEUE_INFORM_EMAIL,true,false,false,null);
        channel.queueDeclare(QUEUE_INFORM_SMS,true,false,false,null);

        /**
                      * 声明队列，如果Rabbit中没有此队列将自动创建
                      * param1:队列名称
                      * param2:是否持久化
                      * param3:队列是否独占此连接
                      * param4:队列不再使用时是否自动删除此队列
                      * param5:队列参数
                      */

        channel.exchangeBind(QUEUE_INFORM_EMAIL,EXCHANGE_FANOUT_INFORM,"");//发布订阅routing key为空
        channel.exchangeBind(QUEUE_INFORM_EMAIL,EXCHANGE_FANOUT_INFORM,"");//发布订阅routing key为空
        for(int i=0;i<5;i++){
            String msg = "send message"+i+"";
            channel.basicPublish(EXCHANGE_FANOUT_INFORM,"",null,msg.getBytes());// exchanage 暂时为“”,默认为空走第一个amqp，按照routingkey来转发队列；routekey为“", 后两个参数为消息内容（property为消息的属性），msg为实体内容
        }

        //5. 管理链接
        channel.close();
        connection.close();
    }
}
