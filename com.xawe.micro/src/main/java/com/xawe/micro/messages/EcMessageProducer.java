package com.xawe.micro.messages;

public interface EcMessageProducer {

	void GenerateMessage(com.xawe.micro.requests.EcRequest ec, String message, String routingKey);
}
