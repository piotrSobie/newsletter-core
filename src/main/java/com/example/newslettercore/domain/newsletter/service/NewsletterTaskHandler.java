package com.example.newslettercore.domain.newsletter.service;

import com.example.newslettercore.domain.message.broker.NewsletterNotifier;
import com.example.newslettercore.domain.newsletter.model.Newsletter;
import com.example.newslettercore.domain.newsletter.model.NewsletterTask;
import com.example.newslettercore.domain.newsletter.model.NewsletterTaskData;
import com.example.newslettercore.domain.newsletter.model.NewsletterTaskStatus;
import com.example.newslettercore.domain.newsletter.repository.NewsletterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class NewsletterTaskHandler implements Runnable {

    private final NewsletterRepository newsletterRepository;
    private final NewsletterNotifier newsletterNotifier;
    private final NewsletterTask newsletterTask;

    @Override
    public void run() {

        // todo get subscriptionId list using newsletterId
        List<String> subscriptionIds = List.of("sub1", "sub2");
        NewsletterTaskData newsletterTaskData = new NewsletterTaskData(newsletterTask.getNewsletterId(), subscriptionIds);
        newsletterNotifier.notify(newsletterTaskData);
        Newsletter newsletter = newsletterRepository.findNewsletterById(newsletterTask.getNewsletterId()).orElseThrow();
        setNewsletterTaskForNextTrigger(newsletterTask, newsletter.getCronSendingFrequency());
        log.info("Successfully handled task for newsletterId {}", newsletterTask.getNewsletterId());
    }

    private void setNewsletterTaskForNextTrigger(NewsletterTask newsletterTask, String cronExpression) {

        newsletterTask.updateNewsletterStatus(NewsletterTaskStatus.NOT_SENT);
        newsletterTask.updateNextTrigger(cronExpression);
        newsletterRepository.saveNewsletterTask(newsletterTask);
    }
}
