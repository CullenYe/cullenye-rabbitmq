package com.cullenye.spring;

public class MyConsumer {
	
	/**
	 * 用于接收消息
	 * @param message
	 */
	public void test(String message) {
        System.err.println(message);
    }
}
