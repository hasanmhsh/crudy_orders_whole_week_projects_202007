package com.lambdaschool.demo.services;

import com.lambdaschool.demo.models.Agent;
import com.lambdaschool.demo.models.Customer;
import com.lambdaschool.demo.models.Order;

import java.util.List;

public interface OrderService {
    public Order findOrderByNum(long num);
    public List<Order> findOrdersWithAdvanceAmountGreaterThanZero();
    //crud
    public void delete(long num);
    public Order save(Order order);
//    public Order update(Order order, long num);
}
