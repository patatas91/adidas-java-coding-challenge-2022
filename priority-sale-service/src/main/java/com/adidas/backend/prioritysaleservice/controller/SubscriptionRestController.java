package com.adidas.backend.prioritysaleservice.controller;

import com.adidas.backend.prioritysaleservice.dto.AdiClubMemberInfoDto;
import com.adidas.backend.prioritysaleservice.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/subscribe")
public class SubscriptionRestController {

  @Autowired
  private SubscriptionService subscriptionService;

  @GetMapping
  public ResponseEntity<AdiClubMemberInfoDto> subscribe(
      @RequestParam(value = "emailAddress") final String emailAddress) {
    log.info("PRIORITY-SALE service /subscribe - Requested subscription for {}", emailAddress);

    AdiClubMemberInfoDto result = subscriptionService.subscription(emailAddress);

    return ResponseEntity
            .ok()
            .body(result);
  }
}
