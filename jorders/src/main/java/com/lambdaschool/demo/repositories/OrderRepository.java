package com.lambdaschool.demo.repositories;

import com.lambdaschool.demo.models.Customer;
import com.lambdaschool.demo.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
