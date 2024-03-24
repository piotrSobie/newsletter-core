package com.example.newslettercore.domain.message.broker;

import com.example.newslettercore.domain.newsletter.model.NewsletterTaskData;

public interface NewsletterNotifier {

    void notify(NewsletterTaskData newsletterTaskData);
}
