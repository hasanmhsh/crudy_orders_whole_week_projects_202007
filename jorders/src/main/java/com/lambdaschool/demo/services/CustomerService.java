package com.lambdaschool.demo.services;

import com.lambdaschool.demo.models.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> findAllCustomers();
    public Customer findCustomerById(long id);
    public List<Customer> findByPartOfName(String part);
}
