package com.bee.springboot.controller.rabbitmq;


import com.bee.springboot.config.RabbitmqConfig;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController  //注解相当于@ResponseBody ＋ @Controller合在一起的作用
@RequestMapping("/rabbit")
@Api(tags = "测试swager")
public class RabbitController {
	private static final Logger logger = LoggerFactory.getLogger(RabbitController.class);
	@Autowired
	RabbitTemplate rabbitTemplate;


	/**
	 * http://localhost:8882/user/send
	 */
	@RequestMapping(value = "/send")
	public void exportExcel() {
		String message = "send email message to user";

/**
 * 参数：
 * 1、交换机名称
 * 2、routingKey
 * 3、消息内容
 */

        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,"inform.email",message);
        System.out.println("生产者发送了消息");
		/**
		 *  生产者发送了消息
		 2019/10/05-21:40:27 [http-nio-8882-exec-2] DEBUG org.springframework.web.servlet.DispatcherServlet- Null ModelAndView returned to DispatcherServlet with name 'dispatcherServlet': assuming HandlerAdapter completed request handling
		 2019/10/05-21:40:27 [http-nio-8882-exec-2] DEBUG org.springframework.web.servlet.DispatcherServlet- Successfully completed request
		 receive message is:send email message to user
		 */
	}

	//使用rabbitTemplate发送消息
	/*@Test
	public void testSendPostPage(){

		Map message = new HashMap<>();
		message.put("pageId","5a795ac7dd573c04508f3a56");
		//将消息对象转成json串
		String messageString = JSON.toJSONString(message);
		//路由key，就是站点ID
		String routingKey = "5a751fab6abb5044e0d19ea1";
		*//**
		 * 参数：
		 * 1、交换机名称
		 * 2、routingKey
		 * 3、消息内容
		 *//*
		rabbitTemplate.convertAndSend("ex_routing_cms_postpage",routingKey,messageString);

	}*/


}  
