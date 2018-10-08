package com.cullenye.helloword;

import com.cullenye.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 
 * @author CullenYe
 *
 */
public class Receiver {
	
	private final static String QUEUE = "testhelloword";//队列的名字
	
	public static void main(String[] args) throws Exception{
		
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE, false, false, false, null);
		
		QueueingConsumer consumer = new QueueingConsumer(channel);//定义一个消费者,QueueingConsumer已经过时,建议使用DefaultConsumer子类
		//接收消息 ,参数2 是自动确认
		channel.basicConsume(QUEUE, true, consumer);
		
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println("收到了一条消息:---"+message);
			
		}
	}
}
