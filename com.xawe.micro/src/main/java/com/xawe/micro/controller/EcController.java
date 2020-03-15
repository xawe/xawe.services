package com.xawe.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.xawe.micro.cases.EcStarter;
import com.xawe.micro.messages.EcMessageProducer;
import com.xawe.micro.requests.*;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class EcController {
	
	//@Autowired EcMessageProducer ecMessage;
	@Autowired EcStarter starter;
	
	@RequestMapping(value="/EC")
	public ResponseEntity<Object> getEc(){
		return new ResponseEntity<>("Teste ok", HttpStatus.OK );
	}
	
	@RequestMapping(value="/EC", method = RequestMethod.POST)
	public ResponseEntity<Object> createEc(@RequestBody EcRequest data){
		
		//ecMessage.GenerateMessage();
		EcRequest retorno = starter.StartProcess(data);		
		return new ResponseEntity<>(retorno, HttpStatus.OK);
	}
	
}
