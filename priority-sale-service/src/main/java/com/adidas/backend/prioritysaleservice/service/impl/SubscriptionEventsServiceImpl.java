package com.adidas.backend.prioritysaleservice.service.impl;

import com.adidas.backend.prioritysaleservice.dto.AdiClubMemberInfoDto;
import com.adidas.backend.prioritysaleservice.event.Event;
import com.adidas.backend.prioritysaleservice.event.SubscriptionEvent;
import com.adidas.backend.prioritysaleservice.service.SubscriptionEventsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionEventsServiceImpl implements SubscriptionEventsService {

    private final KafkaTemplate<String, Event<?>> kafkaTemplate;
    @Value("${topic.name.members}")
    private String members_topic;
    @Value("${topic.name.non.members}")
    private String non_members_topic;

    @Override
    public void publish(AdiClubMemberInfoDto member) {
        SubscriptionEvent subscription = new SubscriptionEvent();
        subscription.setId(UUID.randomUUID().toString());
        subscription.setData(member);

        if (member.getRegistrationDate() != null) {
            log.info("Members queue");
            this.send(members_topic, subscription);

        } else {
            log.info("Non members queue");
            this.send(non_members_topic, subscription);

        }
        log.info("Published!!");
    }

    private void send(String topic, SubscriptionEvent member) {
        ListenableFuture<SendResult<String, Event<?>>> future =
                kafkaTemplate.send(topic, member);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Event<?>> result) {
                SubscriptionEvent message = (SubscriptionEvent) result.getProducerRecord().value();
                System.out.println("Sent message=[" + message.getData().toString() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + member.getData().getEmail() + "] due to : " + ex.getMessage());
            }
        });

        /*
        // create a producer record
        ProducerRecord<String, Event<?>> producerRecord =
                new ProducerRecord<>(topic, member);

        // send data - asynchronous
        log.info("kafka send...");
        kafkaTemplate.send(producerRecord);
        log.info("sended");
         */
    }
}
