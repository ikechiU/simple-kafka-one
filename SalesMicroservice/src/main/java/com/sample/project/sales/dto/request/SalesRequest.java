package com.sample.project.sales.dto.request;

import com.sample.project.sales.enums.Status;
import com.sample.project.sales.model.Item;
import com.sample.project.sales.model.Sales;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author Ikechi Ucheagwu
 * @created 19/03/2023 - 05:03
 * @project Simple-Kafka-Project
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesRequest {
    private List<Item> items;
    private BigDecimal amount;

    public static Sales mapToSales(SalesRequest request) {
        return Sales.builder()
                .trackingId(UUID.randomUUID().toString())
                .status(Status.PENDING)
                .items(request.getItems())
                .amount(request.getAmount())
                .build();
    }
}
