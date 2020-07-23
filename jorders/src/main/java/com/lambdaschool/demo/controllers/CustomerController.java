package com.lambdaschool.demo.controllers;

import com.lambdaschool.demo.models.Customer;
import com.lambdaschool.demo.models.Order;
import com.lambdaschool.demo.services.CustomerService;
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
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/orders",
            produces = "application/json")
    public ResponseEntity<?> listAllCustomers()
    {
        List<Customer> customers = customerService.findAllCustomers();
        return new ResponseEntity<>(customers,
                HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{id}",
            produces = "application/json")
    public ResponseEntity<?> getCustomerById
            (@PathVariable Long id)
    {
        Customer customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(customer,
                HttpStatus.OK);
    }

    @GetMapping(value = "/namelike/{part}",
            produces = "application/json")
    public ResponseEntity<?> getCustomerById
            (@PathVariable String part)
    {
        List<Customer> customers = customerService.findByPartOfName(part);
        return new ResponseEntity<>(customers,
                HttpStatus.OK);
    }

    //crud


    //that creates new raw in table
    @PostMapping(value = "/customer",
            consumes = "application/json")
    public ResponseEntity<?> addNewCustomer(
            @Valid
            @RequestBody
                    Customer newCustomer)
    {
        // ids are not recognized by the Post method
        newCustomer.setCustcode(0);
        newCustomer = customerService.save(newCustomer);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{custCode}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    //that replaces an existing raw with new one
    @PutMapping(value = "/customer/{custcode}",
            consumes = "application/json")
    public ResponseEntity<?> replaceCustomer(
            @Valid
            @RequestBody
                    Customer newCustomer,
            @PathVariable long custcode)
    {
        // ids are not recognized by the Post method
        newCustomer.setCustcode(custcode);
        customerService.save(newCustomer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //that updates the values of existing row fields
    @PatchMapping(value = "/customer/{custcode}",
            consumes = "application/json")
    public ResponseEntity<?> updateCustomer(
            @RequestBody
                    Customer updateCustomer,
            @PathVariable
                    long custcode)
    {
        customerService.update(updateCustomer,
                custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/customer/{custcode}")
    public ResponseEntity<?> deleteCustomerByCode(
            @PathVariable
                    long custcode)
    {
        customerService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
