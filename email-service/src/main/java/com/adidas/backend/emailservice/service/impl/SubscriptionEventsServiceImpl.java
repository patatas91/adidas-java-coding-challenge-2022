package com.adidas.backend.emailservice.service.impl;

import com.adidas.backend.emailservice.dto.AdiClubMemberInfoDto;
import com.adidas.backend.emailservice.event.Event;
import com.adidas.backend.emailservice.event.SubscriptionEvent;
import com.adidas.backend.emailservice.service.SubscriptionEventsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SubscriptionEventsServiceImpl implements SubscriptionEventsService {

    private static final String TOPIC = "subscription-topic";

    @KafkaListener(
            topics = TOPIC,
            containerFactory = "kafkaListenerContainerFactory", // listener name
            groupId = "group1"
    )
    @Override
    public void consumer(Event<?> event) {
        if (event.getClass().isAssignableFrom(SubscriptionEvent.class)) {
            SubscriptionEvent subscriptionEvent = (SubscriptionEvent) event;
            AdiClubMemberInfoDto member = (AdiClubMemberInfoDto) event.getData();
            log.info("Send email to {}", member.getEmail());
            log.info("EMAIL!!");
        }
    }
}
