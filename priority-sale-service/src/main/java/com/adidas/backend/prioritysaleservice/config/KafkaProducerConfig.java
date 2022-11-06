package com.adidas.backend.prioritysaleservice.config;

import com.adidas.backend.prioritysaleservice.event.Event;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    private final String busAddress = "localhost:9092";

    /**
     * Kafka config template
     * @return
     */
    @Bean
    public ProducerFactory<String, Event<?>> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        // Bus address
        config.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, busAddress);

        // Key serializer -> STRING
        config.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Object serializer -> JSON
        config.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, Event<?>> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
