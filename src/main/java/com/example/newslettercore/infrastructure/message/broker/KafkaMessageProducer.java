package com.example.newslettercore.infrastructure.message.broker;

import com.example.newslettercore.domain.message.broker.MessageBrokerProducer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.example.newslettercore.infrastructure.configuration.KafkaMessageProducerConfig.DEFAULT_JSON_PRODUCER_BEAN;

@Service
public class KafkaMessageProducer implements MessageBrokerProducer {

    private final KafkaTemplate<String, Object> kafkaJsonTemplate;

    public KafkaMessageProducer(@Qualifier(DEFAULT_JSON_PRODUCER_BEAN) KafkaTemplate<String, Object> kafkaJsonTemplate) {

        this.kafkaJsonTemplate = kafkaJsonTemplate;
    }

    @Override
    public void sendMessage(String destination, Object message) {

        kafkaJsonTemplate.send(destination, message);
    }
}
