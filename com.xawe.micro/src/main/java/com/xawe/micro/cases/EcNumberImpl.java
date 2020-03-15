package com.xawe.micro.cases;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EcNumberImpl implements EcNumber{
	@Bean
	public String CreateEcNumber() {
		Random rand = new Random();		
		String r ;
		r =  String.valueOf(rand.nextInt(99999999));		
		return r;
	}
}
