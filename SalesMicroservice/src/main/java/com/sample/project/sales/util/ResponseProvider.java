package com.sample.project.sales.util;

import com.sample.project.sales.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResponseProvider {
    public ResponseEntity<ApiResponse<Object>> success(Object payload) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>("Request Successful", true, payload));
    }

    public ResponseEntity<ApiResponse<Object>> success(String message, Object payload) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(message, true, payload));
    }
}