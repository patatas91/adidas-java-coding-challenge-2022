package com.adidas.backend.prioritysaleservice.service.impl;

import com.adidas.backend.prioritysaleservice.dto.AdiClubMemberInfoDto;
import com.adidas.backend.prioritysaleservice.event.SubscriptionEvent;
import com.adidas.backend.prioritysaleservice.service.SubscriptionEventsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class SubscriptionEventsServiceImpl implements SubscriptionEventsService {

    @Autowired
    private KafkaTemplate producer;

    private static final String TOPIC = "subscription-topic";

    @Override
    public void publish(AdiClubMemberInfoDto member) {
        SubscriptionEvent subscription = new SubscriptionEvent();
        subscription.setId(UUID.randomUUID().toString());
        subscription.setDate(new Date());
        subscription.setData(member);

        //this.producer.send(TOPIC, member);
        log.info("Published!!");
    }
}
