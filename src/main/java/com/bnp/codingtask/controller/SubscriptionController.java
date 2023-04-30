package com.bnp.codingtask.controller;

import com.bnp.codingtask.entity.Subscription;
import com.bnp.codingtask.entity.Quotation;
import com.bnp.codingtask.repository.QuotationRepository;
import com.bnp.codingtask.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private QuotationRepository quotationRepository;

    @PostMapping
    public Subscription createSubscription(@RequestBody Subscription subscription) {
        Quotation quotation = quotationRepository.findById(subscription.getQuotation().getId()).orElseThrow();
        subscription.setQuotation(quotation);
        return subscriptionRepository.save(subscription);
    }

    @GetMapping("/{id}")
    public Subscription getSubscription(@PathVariable Long id) {
        return subscriptionRepository.findById(id).orElseThrow();
    }
}
