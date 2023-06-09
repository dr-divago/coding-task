package com.bnp.codingtask.controller;

import com.bnp.codingtask.entity.Quotation;
import com.bnp.codingtask.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotations")
public class QuotationController {

    @Autowired
    private QuotationRepository quotationRepository;

    @PostMapping
    public Quotation createQuotation(@RequestBody Quotation quotation) {
        return quotationRepository.save(quotation);
    }
}

