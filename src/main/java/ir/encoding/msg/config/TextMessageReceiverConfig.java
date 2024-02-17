package ir.encoding.msg.config;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component @Slf4j
public class TextMessageReceiverConfig {

	public void receive(String message) {
		log.info(message);
	}
	
}
