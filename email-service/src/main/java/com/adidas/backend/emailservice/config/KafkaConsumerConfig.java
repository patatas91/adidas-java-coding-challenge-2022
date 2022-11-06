package com.adidas.backend.emailservice.config;

import com.adidas.backend.emailservice.event.Event;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    private final String busAddress = "localhost:9092";

    /**
     * Kafka config consumer
     * @return
     */
    @Bean
    public ConsumerFactory<String, Event<?>> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        // Bus address
        config.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, busAddress);

        // Trusted class
        config.put(
                JsonSerializer.TYPE_MAPPINGS, "com.adidas.backend.emailservice.event.Event");

        final JsonDeserializer<Event<?>> jsonDeserializer = new JsonDeserializer<>();
        return new DefaultKafkaConsumerFactory<>(
                config,
                new StringDeserializer(),
                jsonDeserializer
        );
    }

    /**
     * Listener
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Event<?>>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Event<?>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
