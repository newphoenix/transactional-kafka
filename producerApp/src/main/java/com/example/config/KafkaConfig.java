package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

	@Value("${tpd.topic-name}")
	private String topicName;
	
	@Value("${spring.kafka.producer.transaction-id-prefix}")
	private String tansaction_prefix;

	private final KafkaProperties kafkaProperties;

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	@Bean
	public ProducerFactory<String, Object> producerFactory() {		
		DefaultKafkaProducerFactory<String, Object> pf = new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
		pf.setTransactionIdPrefix(tansaction_prefix);
		return pf;
	}

	@Bean
	public NewTopic adviceTopic() {
		return new NewTopic(topicName, 3, (short) 1);
	}

	@Bean
	public NewTopic deadLetterTopic(KafkaProperties properties) {

		return new NewTopic(topicName + ".DLT", 1, (short) 1);
	}

}
