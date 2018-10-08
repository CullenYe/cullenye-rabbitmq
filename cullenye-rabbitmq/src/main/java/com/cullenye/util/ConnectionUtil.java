package com.cullenye.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * @author CullenYe
 *
 */
public class ConnectionUtil {

	/**
	 * 用于创建连接的工具类
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("192.168.3.195");	//设置 server 的地址
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("test");
		connectionFactory.setPassword("test");
		connectionFactory.setVirtualHost("/test");
		return connectionFactory.newConnection();			//创建一个新的连接
	}
	
}
