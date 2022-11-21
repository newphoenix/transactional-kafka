package com.example.receiver;

import javax.validation.Valid;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.model.Message;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Consumer {
	
	@KafkaListener(//
			topics = "message-topic", //
			containerFactory = "kafkaListenerContainerFactory")
	public void listenAsObject(//
			ConsumerRecord<String, Message> cr, //
			@Payload @Valid Message payload) {
		
		log.info("### Logger 1 [Object] received key: {}| Payload: {}", cr.key(), payload);

	}

}
