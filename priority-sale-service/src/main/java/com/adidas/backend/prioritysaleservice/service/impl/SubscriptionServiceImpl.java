package com.adidas.backend.prioritysaleservice.service.impl;

import com.adidas.backend.prioritysaleservice.dto.AdiClubMemberInfoDto;
import com.adidas.backend.prioritysaleservice.service.SubscriptionEventsService;
import com.adidas.backend.prioritysaleservice.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private static final String URL = "http://localhost:8082/adiclub";

    @Autowired
    private SubscriptionEventsService subscriptionEventsService;

    @Override
    public AdiClubMemberInfoDto subscription(String emailAddress) {
        // Requesting member info
        log.info("Requesting member info to ADI-CLUB service...");
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("emailAddress", emailAddress).encode().toUriString();
        log.info("Url -> {}", urlTemplate);

        RestTemplate restTemplate = new RestTemplate();
        AdiClubMemberInfoDto member;

        try {
            ResponseEntity<AdiClubMemberInfoDto> response =
                    restTemplate.getForEntity(
                            urlTemplate,
                            AdiClubMemberInfoDto.class);
            member = response.getBody();

        } catch(HttpStatusCodeException e) {
            if (e.getStatusCode().value() == 404) {
                log.info("Member not registered");
                member = new AdiClubMemberInfoDto(emailAddress, null, null);

            } else {
                log.error(e.getMessage());
                throw e;
            }
        }

        log.info("Member getted -> {}", member);

        // Add member to queue
        subscriptionEventsService.publish(member);

        return member;
    }
}
