package com.adidas.backend.publicservice.controller;

import com.adidas.backend.publicservice.dto.AdiClubMemberInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping(value = "/subscribe")
public class SubscriptionRestController {

  private static final String SUBSCRIBE_RESPONSE = "Succesfully subscribed!!";
  private static final String URL = "http://localhost:8081/subscribe";

  @GetMapping
  public ResponseEntity<String> subscribe(
          @RequestParam(value = "emailAddress") final String emailAddress) {
    log.info("PUBLIC service /subscribe - Requested subscription for {}", emailAddress);
    String urlTemplate = UriComponentsBuilder.fromHttpUrl(URL)
            .queryParam("emailAddress", emailAddress).encode().toUriString();
    log.info("Url -> {}", urlTemplate);

    log.info("Requesting to PRIORITY-SALE service...");
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<AdiClubMemberInfoDto> response =
            restTemplate.getForEntity(
                    urlTemplate,
                    AdiClubMemberInfoDto.class);
    AdiClubMemberInfoDto result = response.getBody();
    log.info("Member getted -> {}", result);

    return ResponseEntity
        .ok()
        .body(SUBSCRIBE_RESPONSE);
  }

}
