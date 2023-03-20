package com.sample.project.sales.controller;

import com.sample.project.sales.dto.ApiResponse;
import com.sample.project.sales.dto.request.SalesRequest;
import com.sample.project.sales.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sample.project.sales.util.ResponseProvider;

/**
 * @author Ikechi Ucheagwu
 * @created 19/03/2023 - 04:42
 * @project Simple-Kafka-Project
 */

@RestController("api/v1")
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;
    private final ResponseProvider responseProvider;

    @PostMapping("/sales")
    public ResponseEntity<ApiResponse<Object>> initiateSales(@RequestBody SalesRequest request) {
        return responseProvider.success(salesService.initiateSales(request));
    }

    @GetMapping("/sales-record/{trackingId}")
    public ResponseEntity<ApiResponse<Object>> documentedSales(@PathVariable String trackingId) {
        return responseProvider.success(salesService.documentedSales(trackingId));
    }
}
