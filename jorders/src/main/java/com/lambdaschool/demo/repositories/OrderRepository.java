package com.lambdaschool.demo.repositories;

import com.lambdaschool.demo.models.Customer;
import com.lambdaschool.demo.models.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    public List<Order> findByAdvanceamountGreaterThan(double number);
}
