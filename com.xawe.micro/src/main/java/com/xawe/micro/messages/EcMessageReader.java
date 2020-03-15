package com.xawe.micro.messages;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.xawe.micro.requests.EcRequest;;


@Component
public class EcMessageReader {

	// exemplo de leitura da fila de ec.pf
	@RabbitListener(queues = {"ec.pf"})
	public void receiveMessageFromFanout1(EcRequest message) {
	    System.out.println("Received fanout 1 message: " + message.getEcNumber());
	}
}
