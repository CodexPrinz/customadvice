package com.projects.customadvice.service;

import com.projects.customadvice.exception.CustomerAlreadyExistsException;
import com.projects.customadvice.exception.NoSuchCustomerExistsException;
import com.projects.customadvice.model.Customer;
import com.projects.customadvice.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomer(long id) {
        log.info("[CustomerServiceImpl - getCustomer] - With id: {}", id);
        return customerRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("NO CUSTOMER PRESENT WITH ID = "+ id)
                );
    }

    @Override
    public String addCustomer(Customer customer) {
        log.info("[CustomerServiceImpl - addCustomer]");
        Customer existingCustomer = customerRepository.findById(customer.getId()).orElse(null);
        if (existingCustomer == null){
            customerRepository.save(customer);
            log.info("Customer added successfully");
            return "Customer added successfully";
        }
        else {
            log.info("Customer already exists!!");
            throw new CustomerAlreadyExistsException("Customer already exists!!");
        }

    }

    @Override
    public String updateCustomer(Customer customer) {
        log.info("[CustomerServiceImpl - updateCustomer]");
        Customer existingCustomer = customerRepository.findById(customer.getId()).orElse(null);
        if (existingCustomer == null){
            log.info("No Such Customer exists!!");
            throw new NoSuchCustomerExistsException("No Such Customer exists!!");
        }
        else {
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setName(customer.getName());
            customerRepository.save(existingCustomer);
            log.info("Record updated Successfully");
            return "Record updated Successfully";
        }
    }
}
