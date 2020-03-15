package com.xawe.micro.cases;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xawe.micro.messages.EcMessageProducer;

@Component
public class EcStarterImpl implements EcStarter{

	@Autowired EcNumber ecNumber;
	@Autowired EcMessageProducer messageProducer;
	
	public com.xawe.micro.requests.EcRequest StartProcess(com.xawe.micro.requests.EcRequest ec) {
		Random rand = new Random();
		ec.setEcNumber(ecNumber.CreateEcNumber());		
		ec.setId(rand.nextInt(9999));
		
		String routingKey = "";
		if(ec.getIsPF()!= null && ec.getIsPF()) {
			routingKey = "ec.redirect.pf";
		}
		else {
			routingKey = "ec.redirect.pj";
		}
		
		messageProducer.GenerateMessage(ec, "Ec criado com sucesso", routingKey);
		return ec;
	}
}
