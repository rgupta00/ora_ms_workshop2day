package com.delivery.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.delivery.config.MessageConfig;
import com.delivery.dto.OrderResponse;

@Service
public class OderProcesor {
	
	@RabbitListener(queues = MessageConfig.QUEUE)
	public void processFromQueue(OrderResponse orderResponse) {
		System.out.println("order is processed: "+ orderResponse.toString());
	}

}
