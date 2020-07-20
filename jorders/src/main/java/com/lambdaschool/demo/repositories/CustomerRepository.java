package com.lambdaschool.demo.repositories;

import com.lambdaschool.demo.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
