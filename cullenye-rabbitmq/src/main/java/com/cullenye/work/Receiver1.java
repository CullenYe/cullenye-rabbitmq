package com.cullenye.work;

import java.io.IOException;

import com.cullenye.util.ConnectionUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * 
 * @author CullenYe
 *
 */
public class Receiver1 {
	
	private final static String QUEUE = "testwork";//队列的名字
	
	public static void main(String[] args) throws Exception{
		
		Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE,false,false,false,null);
        channel.basicQos(1);//告诉服务器,在我们没有确认当前消息完成之前,不要给我发新的消息,以此实现处理快的消费者多分配消息，而不是平均分配
        DefaultConsumer consumer = new DefaultConsumer(channel) {
        	
        	@Override
        	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        		//当我们收到消息的时候调用
        		System.out.println("消费者1 收到的内容是："+new String(body));
        		try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};//模拟耗时
        		//确认
        		channel.basicAck(envelope.getDeliveryTag(), false);//参数2,false 为确认收到消息, true 为拒接收到消息
        	}
        };
        
        //注册消费者, 参数2 手动确认,代表我们收到消息后需要手动告诉服务器,我收到消息了
        channel.basicConsume(QUEUE, false,consumer);
	}

}
