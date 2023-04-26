package com.matteo.coding.task.codingtask.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate beginningOfInsurance;

    private BigDecimal insuredAmount;

    private LocalDate dateOfSigningMortgage;

    @ManyToOne
    private Customer customer;
}
