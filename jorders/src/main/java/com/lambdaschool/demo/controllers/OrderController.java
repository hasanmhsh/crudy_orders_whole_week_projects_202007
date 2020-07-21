package com.lambdaschool.demo.controllers;

import com.lambdaschool.demo.models.Agent;
import com.lambdaschool.demo.models.Order;
import com.lambdaschool.demo.repositories.OrderRepository;
import com.lambdaschool.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/order/{num}",
            produces = "application/json")
    public ResponseEntity<?> getOrderByNum
            (@PathVariable Long num)
    {
        Order order = orderService.findOrderByNum(num);
        return new ResponseEntity<>(order,
                HttpStatus.OK);
    }

    @GetMapping(value = "/advanceamount",
            produces = "application/json")
    public ResponseEntity<?> getOrderWithAdvanceAmountGreaterThanZero()
    {
        List<Order> orders = orderService.findOrdersWithAdvanceAmountGreaterThanZero();
        return new ResponseEntity<>(orders,
                HttpStatus.OK);
    }
}
