package com.matteo.coding.task.codingtask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matteo.coding.task.codingtask.entity.Customer;
import com.matteo.coding.task.codingtask.entity.Quotation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDate;

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
        // Create a new customer
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setMiddleName("William");
        customer.setEmail("johndoe@example.com");
        customer.setPhoneNumber("123-456-7890");
        customer.setBirthDate(LocalDate.of(1990, 1, 1));

        // Serialize the customer object to JSON
        String customerJson = objectMapper.writeValueAsString(customer);

        // Send a POST request to create the customer
        MvcResult customerResult = mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isOk())
                .andReturn();

        // Deserialize the customer response to a Customer object
        Customer savedCustomer = objectMapper.readValue(customerResult.getResponse().getContentAsString(), Customer.class);

        // Create a new quotation with the saved customer
        Quotation quotation = new Quotation();
        quotation.setBeginningOfInsurance(LocalDate.of(2023, 5, 1));
        quotation.setInsuredAmount(BigDecimal.valueOf(1000000.00));
        quotation.setDateOfSigningMortgage(LocalDate.of(2023, 4, 26));
        quotation.setCustomer(savedCustomer);

        // Serialize the quotation object to JSON
        String quotationJson = objectMapper.writeValueAsString(quotation);

        // Send a POST request to create the quotation
        MvcResult quotationResult = mockMvc.perform(post("/quotations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(quotationJson))
                .andExpect(status().isOk())
                .andReturn();

        // Deserialize the quotation response to a Quotation object
        Quotation savedQuotation = objectMapper.readValue(quotationResult.getResponse().getContentAsString(), Quotation.class);

        // Verify that the saved quotation has the correct customer
        assertEquals(savedCustomer.getId(), savedQuotation.getCustomer().getId());
    }

    @Test
    public void testGetSubscription() throws Exception {
        // Create a new customer
        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setMiddleName("Marie");
        customer.setEmail("janedoe@example.com");
        customer.setPhoneNumber("987-654-3210");
        customer.setBirthDate(LocalDate.of(1995, 6, 15));

        // Serialize the customer object to JSON
        // Send a POST request to create the customer
    }
}

