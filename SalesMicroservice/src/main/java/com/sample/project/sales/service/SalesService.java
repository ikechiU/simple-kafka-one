package com.sample.project.sales.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sample.project.sales.dto.request.SalesRequest;
import com.sample.project.sales.dto.response.SalesResponse;
import com.sample.project.sales.model.Sales;

/**
 * @author Ikechi Ucheagwu
 * @created 19/03/2023 - 04:32
 * @project Simple-Kafka-Project
 */

public interface SalesService {
    SalesResponse initiateSales(SalesRequest request);
    SalesResponse documentedSales(String trackingId);
    void publish(Sales sales);
    void consume(String sales);
}
