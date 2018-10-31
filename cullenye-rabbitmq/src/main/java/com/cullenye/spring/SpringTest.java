package com.cullenye.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		RabbitTemplate template = context.getBean(RabbitTemplate.class);
		template.convertAndSend("spring的消息");
		((ClassPathXmlApplicationContext)context).destroy();
	}
}
