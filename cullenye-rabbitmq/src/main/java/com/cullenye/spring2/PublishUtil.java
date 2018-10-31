package com.cullenye.spring2;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author CullenYe
 *
 */
@Component("publishUtil")
public class PublishUtil {
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send(String exchange, String routingkey, Object message) {
		amqpTemplate.convertAndSend(exchange,routingkey,message);
	}
}
