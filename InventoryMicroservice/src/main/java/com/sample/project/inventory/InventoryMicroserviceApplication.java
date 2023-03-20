package com.sample.project.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ikechi Ucheagwu
 * @created 19/03/2023 - 07:08
 * @project Simple-Kafka-Project
 */

@SpringBootApplication//(scanBasePackages = "com.sample.project.sales.repository")
public class InventoryMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryMicroserviceApplication.class, args);
    }
}
