package com.matteo.coding.task.codingtask.controller;

import com.matteo.coding.task.codingtask.entity.Customer;
import com.matteo.coding.task.codingtask.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setMiddleName(updatedCustomer.getMiddleName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        customer.setBirthDate(updatedCustomer.getBirthDate());
        return customerRepository.save(customer);
    }

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }
}
