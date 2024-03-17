package com.example.newslettercore.domain.newsletter.service;

import com.example.newslettercore.domain.message.broker.MessageBrokerProducer;
import com.example.newslettercore.domain.newsletter.model.Newsletter;
import com.example.newslettercore.domain.newsletter.model.NewsletterTask;
import com.example.newslettercore.domain.newsletter.model.NewsletterTaskData;
import com.example.newslettercore.domain.newsletter.model.NewsletterTaskStatus;
import com.example.newslettercore.domain.newsletter.repository.NewsletterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class NewsletterTaskHandler implements Runnable {

    private final NewsletterRepository newsletterRepository;
    private final MessageBrokerProducer messageBrokerProducer;
    private final String topicName;

    private volatile boolean isAlive = true;

    public void requestStop() {

        isAlive = false;
    }

    @Override
    public void run() {

        while(isAlive) {
            try {
                Optional<NewsletterTask> newsletterTaskOptional = getNewsletterTaskToHandle();
                newsletterTaskOptional.ifPresent(newsletterTask -> {
                    NewsletterTaskData newsletterTaskData = new NewsletterTaskData(newsletterTask.getNewsletterId(), List.of("email1", "email2"));
                    messageBrokerProducer.sendMessage(topicName, newsletterTaskData);
                    Newsletter newsletter = newsletterRepository.findNewsletterById(newsletterTask.getNewsletterId()).orElseThrow();
                    setNewsletterTaskForNextTrigger(newsletterTask, newsletter.getCronSendingFrequency());
                    log.info("Successfully handled task for newsletterId {}", newsletterTask.getNewsletterId());
                });
            } catch (ObjectOptimisticLockingFailureException e) {
                log.info(e.getMessage());
            }
        }
    }

    private synchronized Optional<NewsletterTask> getNewsletterTaskToHandle() {

        Optional<NewsletterTask> optionalNewsletterTask = newsletterRepository.findNewsletterTaskInStatusAndPastTriggerTime(NewsletterTaskStatus.NOT_SENT);
        if (optionalNewsletterTask.isEmpty()) {
            return Optional.empty();
        }

        NewsletterTask newsletterTask = optionalNewsletterTask.get();
        newsletterTask.updateNewsletterStatus(NewsletterTaskStatus.IN_PROGRESS);
        return Optional.of(newsletterRepository.saveNewsletterTask(newsletterTask));
    }

    private synchronized void setNewsletterTaskForNextTrigger(NewsletterTask newsletterTask, String cronExpression) {

        newsletterTask.updateNewsletterStatus(NewsletterTaskStatus.NOT_SENT);
        newsletterTask.updateNextTrigger(cronExpression);
        newsletterRepository.saveNewsletterTask(newsletterTask);
    }
}
