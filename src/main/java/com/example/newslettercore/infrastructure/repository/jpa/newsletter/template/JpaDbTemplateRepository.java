package com.example.newslettercore.infrastructure.repository.jpa.newsletter.template;

import com.example.newslettercore.domain.newsletter.model.NewsletterDTO;
import com.example.newslettercore.domain.newsletter.template.model.TemplateCreateDTO;
import com.example.newslettercore.domain.newsletter.template.model.TemplateDTO;
import com.example.newslettercore.domain.newsletter.template.model.TemplateQueryParams;
import com.example.newslettercore.domain.newsletter.template.repository.TemplateRepository;
import com.example.newslettercore.infrastructure.repository.jpa.newsletter.NewsletterEntity;
import com.example.newslettercore.infrastructure.repository.jpa.newsletter.NewsletterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class JpaDbTemplateRepository implements TemplateRepository {

    private final SpringDataJpaTemplateRepository templateRepository;

    @Autowired
    public JpaDbTemplateRepository(SpringDataJpaTemplateRepository templateRepository) {

        this.templateRepository = templateRepository;
    }


    @Override
    public TemplateDTO save(TemplateCreateDTO templateCreateDTO, NewsletterDTO newsletterDTO) {

        TemplateEntity templateEntity = TemplateMapper.getMapper.mapToTemplateEntity(templateCreateDTO);
        NewsletterEntity newsletterEntity = NewsletterMapper.getMapper.mapToNewsletterEntity(newsletterDTO);
        templateEntity.setNewsletterEntity(newsletterEntity);
        TemplateEntity savedTemplate = templateRepository.save(templateEntity);

        return TemplateMapper.getMapper.mapToTemplateDTO(savedTemplate);
    }

    @Override
    public TemplateDTO update(TemplateDTO templateDTO) {

        TemplateEntity templateEntityFromDb = templateRepository.getReferenceById(templateDTO.getId());
        TemplateEntity templateEntity = TemplateMapper.getMapper.mapToTemplateEntity(templateDTO);
        templateEntity.setNewsletterEntity(templateEntityFromDb.getNewsletterEntity());
        TemplateEntity savedTemplate = templateRepository.save(templateEntity);
        return TemplateMapper.getMapper.mapToTemplateDTO(savedTemplate);
    }

    @Override
    public Collection<TemplateDTO> getByParams(String newsletterId, TemplateQueryParams templateQueryParams) {

        Collection<TemplateEntity> templateEntities = templateRepository.findByParams(newsletterId, templateQueryParams.getCanal());
        return TemplateMapper.getMapper.mapToTemplateDTO(templateEntities);
    }

    @Override
    public Optional<TemplateDTO> findById(String templateId) {

        Optional<TemplateEntity> templateOptional = templateRepository.findById(templateId);

        if (templateOptional.isPresent()) {
            TemplateDTO templateDTO = TemplateMapper.getMapper.mapToTemplateDTO(templateOptional.get());
            return Optional.of(templateDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(String templateId) {

        templateRepository.deleteById(templateId);
    }
}
