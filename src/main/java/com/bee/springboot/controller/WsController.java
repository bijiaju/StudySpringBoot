package com.bee.springboot.controller;

import com.bee.springboot.entity.WiselyMessage;
import com.bee.springboot.entity.WiselyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Websocket的演示
 */
@Controller
public class WsController {

	/**
	 *
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@MessageMapping("/welcome")//功能类似requestmapping
	@SendTo("/topic/getResponse")
	public WiselyResponse say(WiselyMessage message) throws Exception {
		Thread.sleep(3000);
		return new WiselyResponse("Welcome, " + message.getName() + "!");
	}

	@Autowired
	private SimpMessagingTemplate messagingTemplate;//1

	@MessageMapping("/chat")
	public void handleChat(Principal principal, String msg) { //2
		if (principal.getName().equals("wyf")) {//3
			messagingTemplate.convertAndSendToUser("wisely",
					"/queue/notifications", principal.getName() + "-send:"
							+ msg);
		} else {
			messagingTemplate.convertAndSendToUser("wyf",
					"/queue/notifications", principal.getName() + "-send:"
							+ msg);
		}
	}
}