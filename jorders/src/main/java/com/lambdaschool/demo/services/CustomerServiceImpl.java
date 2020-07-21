package com.lambdaschool.demo.services;

import com.lambdaschool.demo.models.Customer;
import com.lambdaschool.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> allCust = new ArrayList<>();
        Iterable<Customer> existingCust = customerRepository.findAll();
        existingCust.iterator().forEachRemaining(allCust::add);
        return allCust;
    }

    @Override
    public Customer findCustomerById(long id) throws
            EntityNotFoundException
    {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer  " + id + " Not Found"));
    }

    @Override
    public List<Customer> findByPartOfName(String part) {
        return customerRepository.findByCustNameContainingIgnoringCase(part);
    }


}
