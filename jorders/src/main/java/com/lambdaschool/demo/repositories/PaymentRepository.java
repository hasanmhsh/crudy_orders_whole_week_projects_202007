package com.lambdaschool.demo.repositories;

import com.lambdaschool.demo.models.Customer;
import com.lambdaschool.demo.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
