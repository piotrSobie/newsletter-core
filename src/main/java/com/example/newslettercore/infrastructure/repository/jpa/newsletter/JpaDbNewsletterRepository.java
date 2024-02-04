package com.example.newslettercore.infrastructure.repository.jpa.newsletter;

import com.example.newslettercore.domain.newsletter.model.NewsletterCreteDTO;
import com.example.newslettercore.domain.newsletter.model.NewsletterDTO;
import com.example.newslettercore.domain.newsletter.model.NewsletterQueryParams;
import com.example.newslettercore.domain.newsletter.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class JpaDbNewsletterRepository implements NewsletterRepository {

    private final SpringDataJpaNewsletterRepository newsletterRepository;

    @Autowired
    public JpaDbNewsletterRepository(SpringDataJpaNewsletterRepository newsletterRepository) {

        this.newsletterRepository = newsletterRepository;
    }

    @Override
    public NewsletterDTO save(NewsletterCreteDTO newsletterCreteDTO) {

        NewsletterEntity newsletterEntity = NewsletterMapper.getMapper.mapToNewsletterEntity(newsletterCreteDTO);
        NewsletterEntity savedNewsletter = newsletterRepository.save(newsletterEntity);
        return NewsletterMapper.getMapper.mapToNewsletterDTO(savedNewsletter);
    }

    @Override
    public NewsletterDTO save(NewsletterDTO newsletterDTO) {

        NewsletterEntity newsletterEntity = NewsletterMapper.getMapper.mapToNewsletterEntity(newsletterDTO);
        NewsletterEntity savedNewsletter = newsletterRepository.save(newsletterEntity);
        return NewsletterMapper.getMapper.mapToNewsletterDTO(savedNewsletter);
    }

    @Override
    public Optional<NewsletterDTO> findById(String newsletterId) {

        Optional<NewsletterEntity> newsletterOptional = newsletterRepository.findById(newsletterId);

        if (newsletterOptional.isPresent()) {
            NewsletterDTO newsletterDTO = NewsletterMapper.getMapper.mapToNewsletterDTO(newsletterOptional.get());
            return Optional.of(newsletterDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Collection<NewsletterDTO> findByParams(NewsletterQueryParams newsletterQueryParams) {

        Collection<NewsletterEntity> newsletterEntities = newsletterRepository.findByParams(newsletterQueryParams.getTag());
        return NewsletterMapper.getMapper.mapToNewsletterDTO(newsletterEntities);
    }

    @Override
    public void deleteById(String newsletterId) {

        newsletterRepository.deleteById(newsletterId);
    }
}
