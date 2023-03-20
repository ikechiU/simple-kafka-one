package com.sample.project.inventory.repository;

import com.sample.project.inventory.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ikechi Ucheagwu
 * @created 19/03/2023 - 05:17
 * @project Simple-Kafka-Project
 */

@Repository
public interface InventoryItemRepository extends JpaRepository<Item, Long> {

}
