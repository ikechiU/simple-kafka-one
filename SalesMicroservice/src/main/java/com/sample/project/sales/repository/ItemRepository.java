package com.sample.project.sales.repository;

import com.sample.project.sales.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ikechi Ucheagwu
 * @created 19/03/2023 - 05:17
 * @project Simple-Kafka-Project
 */

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
