package com.xawe.micro.messages;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class EcMessageProducerImpl implements EcMessageProducer{
	
	private final RabbitTemplate rabbitTemplate;
	
	//fanout exchange é um broadcast do tipo fanout;
	// mensagens enviadas para fanout.exchange serão direcionadas para as filas
	//fanout.queue1, e fanout.queue2
	private final String FANOUT_EXCHANGE_NAME ="fanout.exchange";
	private final String FANOUT_QUEUE_NAME_1 ="fanout.queue1";
	private final String FANOUT_QUEUE_NAME_2 ="fanout.queue2";
	
	//fila responsável por receber a mensagem quando o EC for PF
	// setando o routing key como ec.pf
	private final String FANOUT_TOPIC_NAME_1 ="ec.pf";
	//fila responsável por receber a mensagem quando o EC for PJ
	// setando o routing key como ec.pj
	private final String FANOUT_TOPIC_NAME_2 ="ec.pj";
	// ec.exchange é responsável por direcionar as mensagens recebidas na exchange para as filas
	//ec.pf ou ec.pj, de acordo com o routing key recebido
	private final String TOPIC_EXCHANGE_NAME ="ec.exchange";	
	private final String ROUTING_KEY_EC_PF = "ec.redirect.pf";
	private final String ROUTING_KEY_EC_PJ = "ec.redirect.pj";
	
	public EcMessageProducerImpl(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
		
	public void GenerateMessage(com.xawe.micro.requests.EcRequest ec, String message , String routingKey) {		
		
		//rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, "", "fanout" + message);
		
		
		// os métodos abaixo são responsáveis pelo envio da mensagem as respectivas filas
		if(routingKey.equals("ec.redirect.pj")) {
			rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY_EC_PJ,  ec);
		}
		else {
			rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY_EC_PF,  ec);
		}
						
	}
	
	
	@Bean
	public Declarables topicBindings() {
		Queue topicQueue1 = new Queue(FANOUT_TOPIC_NAME_1, false);
		Queue topicQueue2 = new Queue(FANOUT_TOPIC_NAME_2, false);
		
		TopicExchange topicExchange = new TopicExchange(TOPIC_EXCHANGE_NAME);
		return new Declarables(
				topicQueue1, 
				topicQueue2, 
				topicExchange,
				BindingBuilder
					.bind(topicQueue1)
					.to(topicExchange).with("ec.redirect.pf"),
				BindingBuilder
					.bind(topicQueue2)
					.to(topicExchange).with("ec.redirect.pj")
				);
		
				/*
				//O Bind abaixo indica que routing keys contendo important serão direcionadas a fila topicQueue1
				// exemplo de routing key >> "teste.important.message
				BindingBuilder
					.bind(topicQueue1)
					.to(topicExchange).with("*.important.*"),
				// o Bind abaixo indica que routingkey terminando com error será redirecionado para a topicQueue2
				// exemplo de routing key >> "message.error"
				BindingBuilder
					.bind(topicQueue2)
					.to(topicExchange).with("#.error")
				*/	
							
	}
	
	/* O Exemplo abaixo executa um broadcast, ligando as filas fanout1 e fanout2 ao fanout Exchange
	 * não está em uso. descomentar a linha 43 se quiser fazer testes
	 */
	@Bean
	public Declarables fanoutBindings() {
	
		Queue fanoutQueue1 = new Queue(FANOUT_QUEUE_NAME_1, false);
		Queue fanoutQueue2 = new Queue(FANOUT_QUEUE_NAME_2, false);		
		FanoutExchange fanoutExchange = new FanoutExchange(FANOUT_EXCHANGE_NAME);
		
		return new Declarables(
				fanoutQueue1,
				fanoutQueue2,
				fanoutExchange,
				BindingBuilder.bind(fanoutQueue1).to(fanoutExchange),
				BindingBuilder.bind(fanoutQueue2).to(fanoutExchange)
				);		
	}
}
