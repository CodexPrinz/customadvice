package com.projects.customadvice.controller;

import com.projects.customadvice.exception.CustomerAlreadyExistsException;
import com.projects.customadvice.model.Customer;
import com.projects.customadvice.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projects.customadvice.error.ErrorResponse;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        log.info("[CustomerController - getCustomer]");
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        log.info("[CustomerServiceImpl - addCustomer]");
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        log.info("[CustomerServiceImpl - updateCustomer]");
        return ResponseEntity.ok(customerService.updateCustomer(customer));
    }

}
