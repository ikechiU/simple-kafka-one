package com.sample.project.sales.dto.response;

import com.sample.project.sales.enums.Status;
import com.sample.project.sales.model.Item;
import com.sample.project.sales.model.Sales;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ikechi Ucheagwu
 * @created 19/03/2023 - 05:03
 * @project Simple-Kafka-Project
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesResponse {
    private String trackingId;
    private Status status;
    private List<Item> items;
    private BigDecimal amount;

    public static SalesResponse mapFromSales(Sales sales) {
        return SalesResponse.builder()
                .trackingId(sales.getTrackingId())
                .status(sales.getStatus())
                .items(sales.getItems())
                .amount(sales.getAmount())
                .build();
    }
}
