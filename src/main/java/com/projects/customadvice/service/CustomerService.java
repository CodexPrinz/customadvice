package com.projects.customadvice.service;

import com.projects.customadvice.model.Customer;
import org.springframework.stereotype.Service;


public interface CustomerService {

    Customer getCustomer(long id);

    String addCustomer(Customer customer);

    String updateCustomer(Customer customer);
}
