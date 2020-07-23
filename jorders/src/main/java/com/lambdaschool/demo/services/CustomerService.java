package com.lambdaschool.demo.services;

import com.lambdaschool.demo.models.Agent;
import com.lambdaschool.demo.models.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> findAllCustomers();
    public Customer findCustomerById(long id);
    public List<Customer> findByPartOfName(String part);
    //crud
    public void delete(long id);
    public Customer save(Customer customer);
    public Customer update(Customer customer, long id);
}
