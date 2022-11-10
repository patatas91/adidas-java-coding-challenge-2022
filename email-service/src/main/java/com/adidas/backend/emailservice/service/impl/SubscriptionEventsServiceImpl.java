package com.adidas.backend.emailservice.service.impl;

import com.adidas.backend.emailservice.dto.AdiClubMemberInfoDto;
import com.adidas.backend.emailservice.event.Event;
import com.adidas.backend.emailservice.event.SubscriptionEvent;
import com.adidas.backend.emailservice.exception.NoDataException;
import com.adidas.backend.emailservice.service.SubscriptionEventsService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionEventsServiceImpl implements SubscriptionEventsService {
    private static final Random RANDOM = new Random(System.nanoTime());
    private final KafkaTemplate<String, Event<?>> kafkaTemplate;
    @Value("${topic.name.members}")
    private String members_topic;
    @Value("${topic.name.non.members}")
    private String non_members_topic;

    private List<AdiClubMemberInfoDto> members;
    private List<AdiClubMemberInfoDto> nonMembers;

    @Override
    public AdiClubMemberInfoDto getWinner() {
        // Get members
        log.info("Getting members...");

        if (members != null && !members.isEmpty()) {
            log.info("Members -> {}", members.size());
            // Ordering members
            members.sort(Comparator.comparing(AdiClubMemberInfoDto::getPoints).reversed()
                    .thenComparing(AdiClubMemberInfoDto::getInstant));

            log.info("Result: ");
            members.forEach(System.out::println);

            return members.get(0);

        } else if (nonMembers != null && !nonMembers.isEmpty()) {
            // Get non members
            log.info("No members found, getting non members...");
            log.info("Non members -> {}", nonMembers.size());

            // Random winner
            Random r = new Random();
            return nonMembers.get(r.nextInt(nonMembers.size()));

        } else {
            log.info("No members found!!!");
            throw new NoDataException();
        }
    }

    @KafkaListener(topics = "${topic.name.members}", groupId = "group_id")
    public void consume(ConsumerRecord<String, Event<?>> payload){
        log.info("Topic: {} Payload: {}", members_topic, payload.value());
        SubscriptionEvent event =
                new Gson().fromJson(String.valueOf(payload.value()), SubscriptionEvent.class);
        AdiClubMemberInfoDto member = event.getData();
        log.info("Member data received: {}", member.toString());
        if (this.members == null) {
            members = new ArrayList<>();
        }
        Instant instant = Instant.parse(member.getRegistrationDate());
        log.info("INSTANT: {}", instant);
        members.add(member);
        log.info("Members size: {}", members.size());
    }

    @KafkaListener(topics = "${topic.name.non.members}", groupId = "group_id")
    public void consume2(ConsumerRecord<String, Event<?>> payload){
        log.info("Topic: {} Payload: {}", non_members_topic, payload.value());
        SubscriptionEvent event =
                new Gson().fromJson(String.valueOf(payload.value()), SubscriptionEvent.class);
        AdiClubMemberInfoDto member = event.getData();
        log.info("Non member data received: {}", member.toString());
        if (this.nonMembers == null) {
            nonMembers = new ArrayList<>();
        }
        nonMembers.add(member);
        log.info("NonMembers size: {}", nonMembers.size());
    }

}
