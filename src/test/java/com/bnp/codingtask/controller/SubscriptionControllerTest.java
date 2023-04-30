package com.bnp.codingtask.controller;

import com.bnp.codingtask.entity.Subscription;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bnp.codingtask.entity.Customer;
import com.bnp.codingtask.entity.Quotation;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateSubscription() throws Exception {
        Customer customer = TestUtil.buildCustomer();
        Quotation quotation = TestUtil.buildQuotation(customer);
        String quotationJson = objectMapper.writeValueAsString(quotation);

        MvcResult quotationResult = mockMvc.perform(post("/quotations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(quotationJson))
                .andExpect(status().isOk())
                .andReturn();

        Quotation savedQuotation = objectMapper.readValue(quotationResult.getResponse().getContentAsString(), Quotation.class);
        Subscription subscription = TestUtil.buildSubscription(savedQuotation);

        String subscriptionJson = objectMapper.writeValueAsString(subscription);

        MvcResult subscritionResult = mockMvc.perform(post("/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(subscriptionJson))
                .andExpect(status().isOk())
                .andReturn();

        Subscription savedSubscription = objectMapper.readValue(subscritionResult.getResponse().getContentAsString(), Subscription.class);

        assertEquals(savedQuotation.getId(), savedSubscription.getQuotation().getId());
        assertEquals(savedQuotation.getCustomer().getId(), savedSubscription.getQuotation().getCustomer().getId());
    }

    @Test
    public void testGetSubscription() throws Exception {
        Customer customer = TestUtil.buildCustomer();
        Quotation quotation = TestUtil.buildQuotation(customer);
        String quotationJson = objectMapper.writeValueAsString(quotation);

        MvcResult quotationResult = mockMvc.perform(post("/quotations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(quotationJson))
                .andExpect(status().isOk())
                .andReturn();

        Quotation savedQuotation = objectMapper.readValue(quotationResult.getResponse().getContentAsString(), Quotation.class);
        Subscription subscription = TestUtil.buildSubscription(savedQuotation);

        String subscriptionJson = objectMapper.writeValueAsString(subscription);

        mockMvc.perform(post("/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(subscriptionJson))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult subscritionResult = mockMvc.perform(get("/subscriptions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Subscription savedSubscription = objectMapper.readValue(subscritionResult.getResponse().getContentAsString(), Subscription.class);
        assertEquals(savedSubscription.getQuotation().getId(), savedQuotation.getId());
    }
}

