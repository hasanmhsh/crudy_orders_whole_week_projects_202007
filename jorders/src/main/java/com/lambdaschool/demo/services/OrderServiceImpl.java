package com.lambdaschool.demo.services;

import com.lambdaschool.demo.models.Customer;
import com.lambdaschool.demo.models.Order;
import com.lambdaschool.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order findOrderByNum(long num) throws
            EntityNotFoundException
    {
        return orderRepository.findById(num)
                .orElseThrow(() -> new EntityNotFoundException("Order  " + num + " Not Found"));
    }

    @Override
    public List<Order> findOrdersWithAdvanceAmountGreaterThanZero() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findByAdvanceAmountGreaterThan(0.0).iterator().forEachRemaining(orders::add);
        return orders;
    }
}
