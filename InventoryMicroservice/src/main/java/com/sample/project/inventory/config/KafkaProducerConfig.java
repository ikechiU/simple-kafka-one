package com.sample.project.inventory.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author Ikechi Ucheagwu
 * @created 19/03/2023 - 05:24
 * @project Simple-Kafka-Project
 */

@EnableKafka
@Configuration
public class KafkaProducerConfig {
//    private final KafkaProperties kafkaProperties;
//
//    @Autowired
//    public KafkaProducerConfig(KafkaProperties kafkaProperties) {
//        this.kafkaProperties = kafkaProperties;
//    }
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        // get configs on application.properties/yml
//        Map<String, Object> properties = kafkaProperties.buildProducerProperties();
//        return new DefaultKafkaProducerFactory<>(properties);
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    @Bean
//    public NewTopic topic() {
//        return TopicBuilder
//                .name("order.item.sales")
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }

    @Value("${topic.ack}")
    private String inventoryTopic;

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(inventoryTopic)
                .build();
    }

}
