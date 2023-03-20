package com.sample.project.inventory.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Ikechi Ucheagwu
 * @created 19/03/2023 - 04:46
 * @project Simple-Kafka-Project
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;
    private BigDecimal price;
}
