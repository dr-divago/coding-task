package com.matteo.coding.task.codingtask.controller;

import com.matteo.coding.task.codingtask.entity.Quotation;
import com.matteo.coding.task.codingtask.entity.Subscription;
import com.matteo.coding.task.codingtask.repository.QuotationRepository;
import com.matteo.coding.task.codingtask.repository.SubscriptionRepository;
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
