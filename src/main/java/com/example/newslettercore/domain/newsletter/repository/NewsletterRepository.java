package com.example.newslettercore.domain.newsletter.repository;

import com.example.newslettercore.domain.newsletter.model.NewsletterCreteDTO;
import com.example.newslettercore.domain.newsletter.model.NewsletterDTO;
import com.example.newslettercore.domain.newsletter.model.NewsletterQueryParams;

import java.util.Collection;
import java.util.Optional;

public interface NewsletterRepository {

    NewsletterDTO save(NewsletterCreteDTO newsletterCreteDTO);

    NewsletterDTO save(NewsletterDTO newsletterDTO);

    Optional<NewsletterDTO> findById(String newsletterId);

    Collection<NewsletterDTO> findByParams(NewsletterQueryParams newsletterQueryParams);

    void deleteById(String newsletterId);
}
