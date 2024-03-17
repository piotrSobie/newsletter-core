package com.example.newslettercore.infrastructure.configuration;

import lombok.Data;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka.producers")
public class KafkaMessageProducerConfig {

    @Value("${kafka.topic.schedule-event}")
    private String scheduleEventTopic;

    public static final String DEFAULT_JSON_PRODUCER_BEAN = "defaultJsonProducerKafkaTemplate";

    private final Map<String, String> defaultJsonProducer;

    @Bean(DEFAULT_JSON_PRODUCER_BEAN)
    KafkaTemplate<String, Object> createDefaultJsonProducer() {

        ProducerFactory<String, Object> producerFactory = getProducerFactory(prepareProps(defaultJsonProducer));
        return new KafkaTemplate<>(producerFactory);
    }

    private DefaultKafkaProducerFactory<String, Object> getProducerFactory(Map<String, Object> props) {

        return new DefaultKafkaProducerFactory<>(props);
    }

    private Map<String, Object> prepareProps(Map<String, String> kafkaProducerConfig) {

        return new HashMap<>(kafkaProducerConfig); // Casting Map<String, String> to Map<String, Object>
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic(scheduleEventTopic, 2, (short) 1);
    }
}
