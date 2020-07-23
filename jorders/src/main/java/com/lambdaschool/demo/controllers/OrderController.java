package com.lambdaschool.demo.controllers;

import com.lambdaschool.demo.models.Agent;
import com.lambdaschool.demo.models.Customer;
import com.lambdaschool.demo.models.Order;
import com.lambdaschool.demo.repositories.OrderRepository;
import com.lambdaschool.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    //crud

    @PostMapping(value = "/order",
            consumes = "application/json")
    public ResponseEntity<?> addNewOrderToExistingCustomer(
            @Valid
            @RequestBody
                    Order newOrder)
    {
        // ids are not recognized by the Post method
        newOrder.setOrdnum(0);
        newOrder = orderService.save(newOrder);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ordNum}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();
        responseHeaders.setLocation(newOrderURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    @PutMapping(value = "/order/{ordnum}",
            consumes = "application/json")
    public ResponseEntity<?> replaceOrder(
            @Valid
            @RequestBody
                    Order newOrder,
            @PathVariable long ordnum)
    {
        // ids are not recognized by the Post method
        newOrder.setOrdnum(ordnum);
        orderService.save(newOrder);

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @DeleteMapping("/order/{ordnum}")
    public ResponseEntity<?> deleteOrderByNum(
            @PathVariable
                    long ordnum)
    {
        orderService.delete(ordnum);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
