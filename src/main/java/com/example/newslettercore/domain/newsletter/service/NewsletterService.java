package com.example.newslettercore.domain.newsletter.service;

import com.example.newslettercore.domain.newsletter.model.NewsletterCreteDTO;
import com.example.newslettercore.domain.newsletter.model.NewsletterDTO;
import com.example.newslettercore.domain.newsletter.model.NewsletterQueryParams;
import com.example.newslettercore.domain.newsletter.model.NewsletterUpdateDTO;

import java.util.Collection;

public interface NewsletterService {

    NewsletterDTO createNewsletter(NewsletterCreteDTO newsletterCreteDTO);

    NewsletterDTO getById(String newsletterId);

    Collection<NewsletterDTO> getByParams(NewsletterQueryParams newsletterQueryParams);

    NewsletterDTO updateNewsletter(String newsletterId, NewsletterUpdateDTO newsletterUpdateDTO);

    void deleteNewsletter(String newsletterId);
}
