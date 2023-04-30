package com.bnp.codingtask.controller;

import com.bnp.codingtask.entity.Customer;
import com.bnp.codingtask.entity.Quotation;
import com.bnp.codingtask.entity.Subscription;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestUtil {
    static Subscription buildSubscription(Quotation savedQuotation) {
        Subscription subscription = new Subscription();
        subscription.setId(1L);
        subscription.setQuotation(savedQuotation);
        return subscription;
    }

    static Quotation buildQuotation(Customer customer) {
          return Quotation
                    .builder()
                    .beginningOfInsurance(LocalDate.of(2023, 5, 1))
                    .insuredAmount(BigDecimal.valueOf(1000000.00))
                    .dateOfSigningMortgage(LocalDate.of(2023, 4, 26))
                    .customer(customer)
                    .build();
    }

    static Customer buildCustomer() {
        return Customer
                .builder()
                .id(1L)
                .firstName("Test")
                .lastName("Test")
                .email("test@test.com")
                .birthDate(LocalDate.of(1990, 1, 1))
                .build();
    }
}
