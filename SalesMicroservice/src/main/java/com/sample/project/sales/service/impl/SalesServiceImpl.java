package com.sample.project.sales.service.impl;

import com.sample.project.sales.dto.request.SalesRequest;
import com.sample.project.sales.dto.response.SalesResponse;
import com.sample.project.sales.enums.Status;
import com.sample.project.sales.model.Item;
import com.sample.project.sales.model.Sales;
import com.sample.project.sales.repository.ItemRepository;
import com.sample.project.sales.repository.SalesRepository;
import com.sample.project.sales.service.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ikechi Ucheagwu
 * @created 19/03/2023 - 04:32
 * @project Simple-Kafka-Project
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;
    private final ItemRepository itemRepository;
    private final NewTopic topic;
    private final KafkaTemplate<String, Sales> kafkaTemplate;

    @Override
    public SalesResponse initiateSales(SalesRequest request) {

        //TODO -> add product repository and check product if available and price matches

        List<Item> itemsToSave = new ArrayList<>();
        for (int i = 0; i < request.getItems().size(); i++) {
            Item item = request.getItems().get(i);
            Item saveItem = itemRepository.save(item);
            itemsToSave.add(saveItem);
        }
        request.setItems(itemsToSave);

        Sales salesToSave = SalesRequest.mapToSales(request);
        Sales savedSales = salesRepository.save(salesToSave);

        //SEND TO INVENTORY
        publish(savedSales);

        return SalesResponse.mapFromSales(savedSales);
    }

    @Override
    public SalesResponse documentedSales(String trackingId) {
        Sales sales = salesRepository.findByTrackingId(trackingId)
                .orElseThrow(()-> new RuntimeException("No sales record found"));
        return SalesResponse.mapFromSales(sales);
    }

    @Override
    public void publish(Sales sales) {
        Message<Sales> message = MessageBuilder
                .withPayload(sales)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);

        log.info("sales event produced {}", message);
    }

    @KafkaListener(topics = "${topic.ack}", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(String trackingId) {
        log.info(String.format("Acknowledgement from inventory received in sales service => %s", trackingId));

        try {
            Sales sales = salesRepository.findByTrackingId(trackingId)
                    .orElseThrow(()-> new RuntimeException("No sales record found"));
            sales.setStatus(Status.CONFIRMED);
            Sales savedSales = salesRepository.save(sales);

            log.info("Sales updated {}", savedSales);
        } catch (Exception e) {
            log.info("Sales error while trackingId {}", trackingId);
        }
    }
}
