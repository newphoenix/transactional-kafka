package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Message;
import com.example.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

	private final MessageRepository messageRepository;
	private final KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${tpd.topic-name}")
	private String topicName;

	@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
	public Message save(Message message) {

		kafkaTemplate.send(topicName, message);
		return messageRepository.save(message);
	}

}
