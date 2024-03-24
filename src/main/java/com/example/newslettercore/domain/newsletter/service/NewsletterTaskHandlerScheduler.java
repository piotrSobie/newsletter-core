package com.example.newslettercore.domain.newsletter.service;

import com.example.newslettercore.domain.message.broker.NewsletterNotifier;
import com.example.newslettercore.domain.newsletter.model.NewsletterTask;
import com.example.newslettercore.domain.newsletter.model.NewsletterTaskStatus;
import com.example.newslettercore.domain.newsletter.repository.NewsletterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewsletterTaskHandlerScheduler {

    private final NewsletterRepository newsletterRepository;
    private final NewsletterNotifier newsletterNotifier;

    @Value("${newsletter.task.service.thread.pool}")
    private Integer newsletterTaskServiceThreadPool;

    @Scheduled(cron = "${newsletter.task.scheduler.cron-expression}")
    @SchedulerLock(name = "executeNewsletterTasks")
    public void executeNewsletterTasks() {

        Collection<NewsletterTask> newsletterTasks = newsletterRepository.findAllNewsletterTaskInStatusAndPastTriggerTime(NewsletterTaskStatus.NOT_SENT);
        List<NewsletterTask> reservedTasks = new ArrayList<>();
        newsletterTasks.forEach(task -> {
            task.updateNewsletterStatus(NewsletterTaskStatus.IN_PROGRESS);
            NewsletterTask savedNewsletterTask = newsletterRepository.saveNewsletterTask(task);
            reservedTasks.add(savedNewsletterTask);
        });

        log.info("Reserved {} newsletter tasks to execute.", reservedTasks.size());

        ExecutorService executorService = Executors.newFixedThreadPool(newsletterTaskServiceThreadPool);
        reservedTasks.forEach(reservedTask -> {
            NewsletterTaskHandler newsletterTaskHandler = new NewsletterTaskHandler(newsletterRepository, newsletterNotifier, reservedTask);
            executorService.submit(newsletterTaskHandler);
        });
    }
}
