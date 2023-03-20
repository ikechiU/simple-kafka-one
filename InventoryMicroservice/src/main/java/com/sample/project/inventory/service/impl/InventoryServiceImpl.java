package com.sample.project.inventory.service.impl;

import com.sample.project.inventory.model.Inventory;
import com.sample.project.inventory.model.Item;
import com.sample.project.inventory.repository.InventoryItemRepository;
import com.sample.project.inventory.repository.InventoryRepository;
import com.sample.project.inventory.service.InventoryService;
import com.sample.project.sales.enums.Status;
import com.sample.project.sales.model.Sales;
import com.sample.project.sales.repository.SalesRepository;
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
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final NewTopic topic;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(Sales sales){
        log.info(String.format("Sales event received in inventory service => %s", sales.toString()));

        List<Item> itemsToSave = new ArrayList<>();
        for (int i = 0; i < sales.getItems().size(); i++) {
            Item item = Item.builder()
                    .item(sales.getItems().get(i).getItem())
                    .price(sales.getItems().get(i).getPrice())
                    .build();
            Item saveItem = inventoryItemRepository.save(item);
            itemsToSave.add(saveItem);
        }

        // save the sales event into the database as an inventory
        Inventory inventory = Inventory.builder()
                .salesId(sales.getId())
                .trackingId(sales.getTrackingId()).status(Status.CONFIRMED)
                .items(itemsToSave) //sale.getItems() having a FK which will prevent inventory from persisting it
                .amount(sales.getAmount())
                .build();

        Inventory savedInventory = inventoryRepository.save(inventory);
        log.info("Inventory saved {}", savedInventory);

        publish(savedInventory.getTrackingId());
        log.info("Publish of trackingId {}, to sales", savedInventory.getTrackingId());
    }

    @Override
    public void publish(String trackingId) {
        Message<String> message = MessageBuilder
                .withPayload(trackingId)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);

        log.info("sales event produced {}", message);
    }
}
