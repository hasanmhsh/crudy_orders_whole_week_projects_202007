package com.lambdaschool.demo.services;

import com.lambdaschool.demo.models.Order;

import java.util.List;

public interface OrderService {
    public Order findOrderByNum(long num);
    public List<Order> findOrdersWithAdvanceAmountGreaterThanZero();
}
