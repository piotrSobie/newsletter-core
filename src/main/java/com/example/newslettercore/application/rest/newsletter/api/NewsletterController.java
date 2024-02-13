package com.example.newslettercore.application.rest.newsletter.api;

import com.example.newslettercore.application.rest.newsletter.mapper.RestNewsletterMapper;
import com.example.newslettercore.application.rest.newsletter.model.NewsletterCreteDTO;
import com.example.newslettercore.application.rest.newsletter.model.NewsletterQueryParams;
import com.example.newslettercore.application.rest.newsletter.model.NewsletterResponse;
import com.example.newslettercore.application.rest.newsletter.model.NewsletterUpdateDTO;
import com.example.newslettercore.domain.newsletter.model.Newsletter;
import com.example.newslettercore.domain.newsletter.service.NewsletterService;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = NewsletterController.CONTROLLER_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class NewsletterController {

    public static final String CONTROLLER_ENDPOINT = "/api/v1";
    public static final String NEWSLETTER_ENDPOINT = "/newsletter";

    private final NewsletterService newsletterService;

    @Autowired
    public NewsletterController(NewsletterService newsletterService) {

        this.newsletterService = newsletterService;
    }

    @PostMapping(path = NEWSLETTER_ENDPOINT)
    public ResponseEntity<NewsletterResponse> createNewsletter(@RequestBody @Valid NewsletterCreteDTO newsletterCreteDTO) {

        Newsletter createdNewsletter = newsletterService.createNewsletter(newsletterCreteDTO.getTags(), newsletterCreteDTO.getCronSendingFrequency());
        NewsletterResponse responseNewsletter = RestNewsletterMapper.getMapper.mapToNewsletterDTO(createdNewsletter);
        return new ResponseEntity<>(responseNewsletter, HttpStatus.CREATED);
    }

    @GetMapping(path = NEWSLETTER_ENDPOINT)
    public ResponseEntity<Collection<NewsletterResponse>> findNewslettersByParams(NewsletterQueryParams newsletterQueryParams) {

        Collection<Newsletter> foundNewsletters = newsletterService.getNewslettersByParams(newsletterQueryParams.getTag());
        Collection<NewsletterResponse> responseNewsletters = RestNewsletterMapper.getMapper.mapToNewsletterDTO(foundNewsletters);
        return ResponseEntity.ok(responseNewsletters);
    }

    @GetMapping(path = NEWSLETTER_ENDPOINT + "/{newsletterId}")
    public ResponseEntity<NewsletterResponse> findNewsletterById(@Nonnull @PathVariable("newsletterId") String newsletterId) {

        Newsletter newsletter = newsletterService.getNewsletterById(newsletterId);
        NewsletterResponse responseNewsletter = RestNewsletterMapper.getMapper.mapToNewsletterDTO(newsletter);
        return new ResponseEntity<>(responseNewsletter, HttpStatus.OK);
    }

    @PatchMapping(path = NEWSLETTER_ENDPOINT + "/{newsletterId}")
    public ResponseEntity<NewsletterResponse> updateNewsletter(@Nonnull @PathVariable("newsletterId") String newsletterId,
                                                               @RequestBody @Valid NewsletterUpdateDTO newsletterUpdateDTO) {

        Newsletter updatedNewsletter = newsletterService.updateNewsletter(newsletterId, newsletterUpdateDTO.getTags(),
                newsletterUpdateDTO.getCronSendingFrequency());
        NewsletterResponse responseNewsletter = RestNewsletterMapper.getMapper.mapToNewsletterDTO(updatedNewsletter);
        return new ResponseEntity<>(responseNewsletter, HttpStatus.OK);
    }

    @DeleteMapping(path = NEWSLETTER_ENDPOINT + "/{newsletterId}")
    public ResponseEntity<Void> deleteNewsletter(@Nonnull @PathVariable("newsletterId") String newsletterId) {

        newsletterService.deleteNewsletter(newsletterId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
