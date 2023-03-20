package com.sample.project.inventory.model;

import com.sample.project.sales.enums.Status;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ikechi Ucheagwu
 * @created 19/03/2023 - 06:13
 * @project Simple-Kafka-Project
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long salesId;
    @CreationTimestamp
    private LocalDateTime localDateTime;
    private String trackingId;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @OneToMany
    @ToString.Exclude
    private List<Item> items;
    private BigDecimal amount;
}
