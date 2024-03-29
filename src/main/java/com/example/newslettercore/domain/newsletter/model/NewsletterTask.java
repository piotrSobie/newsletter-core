package com.example.newslettercore.domain.newsletter.model;

import lombok.Getter;
import org.springframework.scheduling.support.CronExpression;

import java.time.LocalDateTime;

@Getter
public class NewsletterTask {

    private String id;

    private String newsletterId;

    private LocalDateTime nextTrigger;

    private NewsletterTaskStatus newsletterTaskStatus;

    private Long version;

    public NewsletterTask(String newsletterId, NewsletterTaskStatus newsletterTaskStatus, String cronSendingFrequency) {

        validateNewsletterId(newsletterId);
        validateNewsletterTaskStatus(newsletterTaskStatus);

        this.newsletterId = newsletterId;
        this.newsletterTaskStatus = newsletterTaskStatus;
        updateNextTrigger(cronSendingFrequency);
    }

    public NewsletterTask(String id, String newsletterId, LocalDateTime nextTrigger, NewsletterTaskStatus newsletterTaskStatus, Long version) {

        validateData(newsletterId, nextTrigger, newsletterTaskStatus);

        this.newsletterId = newsletterId;
        this.nextTrigger = nextTrigger;
        this.newsletterTaskStatus = newsletterTaskStatus;
        this.id = id;
        this.version = version;
    }

    private void validateData(String newsletterId, LocalDateTime nextTrigger, NewsletterTaskStatus newsletterTaskStatus) {

        validateNewsletterId(newsletterId);
        validateNextTrigger(nextTrigger);
        validateNewsletterTaskStatus(newsletterTaskStatus);
    }

    private void validateNewsletterId(String newsletterId) {

        if (null == newsletterId) {
            throw new IllegalArgumentException("NewsletterId can't be null");
        }
    }

    private void validateNewsletterTaskStatus(NewsletterTaskStatus status) {

        if (null == status) {
            throw new IllegalArgumentException("NewsletterTaskStatus can't be null");
        }
    }

    private void validateNextTrigger(LocalDateTime nextTrigger) {

        if (null == nextTrigger) {
            throw new IllegalArgumentException("Date can't be null");
        }
    }

    public void updateNewsletterStatus(NewsletterTaskStatus status) {

        validateNewsletterTaskStatus(status);
        this.newsletterTaskStatus = status;
    }

    public void updateNextTrigger(String stringCronExpression) {

        CronExpression cronExpression = CronExpression.parse(stringCronExpression);
        LocalDateTime newNextTrigger = cronExpression.next(LocalDateTime.now());
        validateNextTrigger(newNextTrigger);
        this.nextTrigger = newNextTrigger;
    }
}
