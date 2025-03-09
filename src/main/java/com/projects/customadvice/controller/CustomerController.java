package com.projects.customadvice.controller;

import com.projects.customadvice.model.Customer;
import com.projects.customadvice.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class CustomerController {

    private final CustomerService customerService;
    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("getCustomer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id){
        log.info("[CustomerController - getCustomer]");
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @PostMapping("addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
        log.info("[CustomerServiceImpl - addCustomer]");
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

    @PutMapping("updateCustomer")
    public ResponseEntity<String> updateCustomer(Customer customer){
        log.info("[CustomerServiceImpl - updateCustomer]");
        return ResponseEntity.ok(customerService.updateCustomer(customer));
    }
}
