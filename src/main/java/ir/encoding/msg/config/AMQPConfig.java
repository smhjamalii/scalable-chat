package ir.encoding.msg.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

	private static final String DEFAULT_EXCHANGE_NAME = "DEX";
	private static final String DEFAULT_QUEUE_NAME = "DEQ";
	
	@Bean
	Queue deq() {
		return new Queue(DEFAULT_QUEUE_NAME);
	}
	
	@Bean
	DirectExchange dex() {
		return new DirectExchange(DEFAULT_EXCHANGE_NAME);
	}
	
	@Bean
	Binding deqToDexBinding(Queue deq, DirectExchange dex) {
		return BindingBuilder.bind(deq).to(dex).with("ir.encoding.deq");
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory cf, MessageListenerAdapter mla) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(cf);
		container.setQueueNames(DEFAULT_QUEUE_NAME);
		container.setMessageListener(mla);
		return container;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(TextMessageReceiverConfig receiverConfig) {
		return new MessageListenerAdapter(receiverConfig, "receive");
	}
}
