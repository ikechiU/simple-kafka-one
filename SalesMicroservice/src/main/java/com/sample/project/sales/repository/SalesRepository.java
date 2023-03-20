package com.sample.project.sales.repository;

import com.sample.project.sales.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ikechi Ucheagwu
 * @created 19/03/2023 - 04:42
 * @project Simple-Kafka-Project
 */

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    Optional<Sales> findByTrackingId(String trackingId);
}
