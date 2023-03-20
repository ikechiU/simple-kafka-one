package com.sample.project.inventory.service;

import com.sample.project.sales.model.Sales;

/**
 * @author Ikechi Ucheagwu
 * @created 19/03/2023 - 04:32
 * @project Simple-Kafka-Project
 */

public interface InventoryService {
    void consume(Sales sales);
    void publish(String string);
}
