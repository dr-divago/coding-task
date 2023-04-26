package com.matteo.coding.task.codingtask.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Quotation quotation;

    private LocalDate startDate;

    private LocalDate validUntil;
}
