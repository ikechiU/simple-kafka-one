package com.sample.project.sales.model;

import com.sample.project.sales.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ikechi Ucheagwu
 * @created 19/03/2023 - 04:42
 * @project Simple-Kafka-Project
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "sales")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackingId;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @OneToMany
    @ToString.Exclude
    private List<Item> items;
    private BigDecimal amount;
}
