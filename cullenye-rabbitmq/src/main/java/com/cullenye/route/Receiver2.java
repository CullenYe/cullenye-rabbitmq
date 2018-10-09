package com.cullenye.route;

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
public class Receiver2 {
	
	private final static String EXCHANGE_NAME = "testeoute";//定义交换机的名字

    public static void main(String[] args) throws Exception{
    	
    	Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare("testroutequeue2", false, false, false,null);
        //绑定队列到交换机
        //参数3 标记,绑定到交换机的时候会指定一个标记,只有和它一样的标记的消息才会被当前消费者收到
        // 绑定队列到交换机,绑定自己的关键字 key 为key,注意在绑定到指定路由(交换机)的时候,该路由必须存在,也就是我们必须先由发送者创建一个路由才可以
        channel.queueBind("testroutequeue2", EXCHANGE_NAME, "key1");
        //如果要接收多个标记,只需要再执行一次即可
        channel.queueBind("testroutequeue2", EXCHANGE_NAME, "key3");
        channel.basicQos(1);
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2222222:"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume("testroutequeue2", false, consumer);
    }
    	
}
