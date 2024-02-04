package com.example.newslettercore.application.rest.newsletter;

import com.example.newslettercore.domain.newsletter.model.NewsletterCreteDTO;
import com.example.newslettercore.domain.newsletter.model.NewsletterDTO;
import com.example.newslettercore.domain.newsletter.model.NewsletterQueryParams;
import com.example.newslettercore.domain.newsletter.model.NewsletterUpdateDTO;
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
    public ResponseEntity<NewsletterDTO> createNewsletter(@RequestBody @Valid NewsletterCreteDTO newsletterCreteDTO) {

        NewsletterDTO newsletterDTO = newsletterService.createNewsletter(newsletterCreteDTO);
        return new ResponseEntity<>(newsletterDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = NEWSLETTER_ENDPOINT)
    public ResponseEntity<Collection<NewsletterDTO>> findNewslettersByParams(NewsletterQueryParams newsletterQueryParams) {

        Collection<NewsletterDTO> newsletters = newsletterService.getByParams(newsletterQueryParams);
        return ResponseEntity.ok(newsletters);
    }

    @GetMapping(path = NEWSLETTER_ENDPOINT + "/{newsletterId}")
    public ResponseEntity<NewsletterDTO> findNewsletterById(@Nonnull @PathVariable("newsletterId") String newsletterId) {

        NewsletterDTO newsletterDTO = newsletterService.getById(newsletterId);
        return new ResponseEntity<>(newsletterDTO, HttpStatus.OK);
    }

    @PatchMapping(path = NEWSLETTER_ENDPOINT + "/{newsletterId}")
    public ResponseEntity<NewsletterDTO> updateNewsletter(@Nonnull @PathVariable("newsletterId") String newsletterId,
                                                 @RequestBody @Valid NewsletterUpdateDTO newsletterUpdateDTO) {

        NewsletterDTO newsletterDTO = newsletterService.updateNewsletter(newsletterId, newsletterUpdateDTO);
        return new ResponseEntity<>(newsletterDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = NEWSLETTER_ENDPOINT + "/{newsletterId}")
    public ResponseEntity<Void> deleteNewsletter(@Nonnull @PathVariable("newsletterId") String newsletterId) {

        newsletterService.deleteNewsletter(newsletterId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
