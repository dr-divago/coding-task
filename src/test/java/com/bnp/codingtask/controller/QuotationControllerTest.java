package com.bnp.codingtask.controller;

import com.bnp.codingtask.entity.Customer;
import com.bnp.codingtask.entity.Quotation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QuotationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateQuotation() throws Exception {

        Customer customer = TestUtil.buildCustomer();
        Quotation quotation = TestUtil.buildQuotation(customer);

        String quotationJson = objectMapper.writeValueAsString(quotation);

        MvcResult quotationResult = mockMvc.perform(post("/quotations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(quotationJson))
                .andExpect(status().isOk())
                .andReturn();

        Quotation savedQuotation = objectMapper.readValue(quotationResult.getResponse().getContentAsString(), Quotation.class);

        assertEquals(customer.getId(), savedQuotation.getCustomer().getId());
    }
}

