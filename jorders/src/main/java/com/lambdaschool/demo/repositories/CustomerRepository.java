package com.lambdaschool.demo.repositories;

import com.lambdaschool.demo.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    public List<Customer> findByCustNameContainingIgnoringCase(String nameLike);
}
