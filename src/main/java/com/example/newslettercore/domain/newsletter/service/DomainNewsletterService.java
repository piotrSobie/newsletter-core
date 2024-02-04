package com.example.newslettercore.domain.newsletter.service;

import com.example.newslettercore.domain.exception.NewsletterCoreObjectNotFoundException;
import com.example.newslettercore.domain.newsletter.model.NewsletterCreteDTO;
import com.example.newslettercore.domain.newsletter.model.NewsletterDTO;
import com.example.newslettercore.domain.newsletter.model.NewsletterQueryParams;
import com.example.newslettercore.domain.newsletter.model.NewsletterUpdateDTO;
import com.example.newslettercore.domain.newsletter.repository.NewsletterRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.Collection;
import java.util.Optional;

public class DomainNewsletterService implements NewsletterService {

    private final NewsletterRepository newsletterRepository;

    public DomainNewsletterService(NewsletterRepository newsletterRepository) {

        this.newsletterRepository = newsletterRepository;
    }

    @Override
    public NewsletterDTO createNewsletter(NewsletterCreteDTO newsletterCreteDTO) {

        return newsletterRepository.save(newsletterCreteDTO);
    }

    @Override
    public NewsletterDTO getById(String newsletterId) {

        Optional<NewsletterDTO> newsletterOptional = newsletterRepository.findById(newsletterId);

        if (newsletterOptional.isEmpty()) {
            throw new NewsletterCoreObjectNotFoundException(NewsletterDTO.class.getSimpleName(), newsletterId);
        }

        return newsletterOptional.get();
    }

    @Override
    public Collection<NewsletterDTO> getByParams(NewsletterQueryParams newsletterQueryParams) {

        return newsletterRepository.findByParams(newsletterQueryParams);
    }

    @Override
    public NewsletterDTO updateNewsletter(String newsletterId, NewsletterUpdateDTO newsletterUpdateDTO) {

        NewsletterDTO newsletterDTO = getById(newsletterId);
        NewsletterDTO updatedNewsletterBeforeSave = updateNewsletterData(newsletterDTO, newsletterUpdateDTO);
        return newsletterRepository.save(updatedNewsletterBeforeSave);
    }

    private NewsletterDTO updateNewsletterData(NewsletterDTO newsletterDTO, NewsletterUpdateDTO newsletterUpdateDTO) {

        if (!CollectionUtils.isEmpty(newsletterUpdateDTO.getTags())) {
            newsletterDTO.setTags(newsletterUpdateDTO.getTags());
        }

        if (Strings.isNotBlank(newsletterUpdateDTO.getCronSendingFrequency())) {
            newsletterDTO.setCronSendingFrequency(newsletterUpdateDTO.getCronSendingFrequency());
        }

        return newsletterDTO;
    }

    @Override
    public void deleteNewsletter(String newsletterId) {

        newsletterRepository.deleteById(newsletterId);
    }
}
