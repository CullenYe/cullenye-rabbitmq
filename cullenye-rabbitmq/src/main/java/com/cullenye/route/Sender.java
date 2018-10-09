package com.cullenye.route;

import com.cullenye.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 
 * @author CullenYe
 *
 */
public class Sender {

	private final static String EXCHANGE_NAME = "testeoute";
	
	public static void main(String[] args) throws  Exception{
		
		Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");//定义路由格式的交换机
        channel.basicPublish(EXCHANGE_NAME, "key1", null, "路由消息aaaaaa".getBytes());
        channel.close();
        connection.close();
	}
}
