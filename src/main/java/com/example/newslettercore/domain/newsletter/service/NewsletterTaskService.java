package com.example.newslettercore.domain.newsletter.service;

import com.example.newslettercore.domain.newsletter.repository.NewsletterRepository;
import com.example.newslettercore.infrastructure.message.broker.KafkaMessageProducer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class NewsletterTaskService {

    private final NewsletterRepository newsletterRepository;
    private final KafkaMessageProducer kafkaMessageProducer;

    @Value("${newsletter.task.service.thread.pool}")
    private Integer newsletterTaskServiceThreadPool;

    @Value("${kafka.topic.schedule-event}")
    private String scheduleEventTopic;

    private List<NewsletterTaskHandler> taskHandlers;

    @PostConstruct
    void postConstruct() {

        ExecutorService executorService = Executors.newFixedThreadPool(newsletterTaskServiceThreadPool);
        taskHandlers = new ArrayList<>();
        for (int i = 0; i < newsletterTaskServiceThreadPool; i++) {
            NewsletterTaskHandler newsletterTaskHandler = new NewsletterTaskHandler(newsletterRepository, kafkaMessageProducer, scheduleEventTopic);
            taskHandlers.add(newsletterTaskHandler);
            executorService.submit(newsletterTaskHandler);
        }
    }

    @PreDestroy
    void preDestroy() {

        taskHandlers.forEach(NewsletterTaskHandler::requestStop);
    }
}
