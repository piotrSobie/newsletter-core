package com.example.newslettercore.infrastructure.message.broker;

import com.example.newslettercore.domain.message.broker.NewsletterNotifier;
import com.example.newslettercore.domain.newsletter.model.NewsletterTaskData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.example.newslettercore.infrastructure.configuration.KafkaMessageProducerConfig.DEFAULT_JSON_PRODUCER_BEAN;

@Service
public class KafkaMessageProducer implements NewsletterNotifier {

    private final KafkaTemplate<String, Object> kafkaJsonTemplate;

    @Value("${kafka.topic.schedule-event}")
    private String scheduleEventTopic;

    public KafkaMessageProducer(@Qualifier(DEFAULT_JSON_PRODUCER_BEAN) KafkaTemplate<String, Object> kafkaJsonTemplate) {

        this.kafkaJsonTemplate = kafkaJsonTemplate;
    }

    @Override
    public void notify(NewsletterTaskData newsletterTaskData) {

        kafkaJsonTemplate.send(scheduleEventTopic, newsletterTaskData);
    }
}
