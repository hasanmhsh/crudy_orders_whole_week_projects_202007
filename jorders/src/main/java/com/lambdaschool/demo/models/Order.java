package com.lambdaschool.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ordNum;

    private double advanceAmount;
    private double ordAmount;
    private String orderDescription;

    @ManyToOne
    @JoinColumn(name = "custCode",
            nullable = false)
    @JsonIgnoreProperties("orderList")
    private Customer customer;

    @ManyToMany()
    @JoinTable(name = "orderspayments",
            joinColumns = @JoinColumn(name = "ordNum"),
            inverseJoinColumns = @JoinColumn(name = "paymentId"))
    @JsonIgnoreProperties("orders")
    Set<Payment> payments = new HashSet<>();
    public Order(){

    }

    public Order(double ordAmount, double advanceAmount, Customer customer, String orderDescription) {
        this.advanceAmount = advanceAmount;
        this.ordAmount = ordAmount;
        this.orderDescription = orderDescription;
        this.customer = customer;
    }

    public long getOrdNum() {
        return ordNum;
    }

    public void setOrdNum(long ordNum) {
        this.ordNum = ordNum;
    }

    public double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public double getOrdAmount() {
        return ordAmount;
    }

    public void setOrdAmount(double ordAmount) {
        this.ordAmount = ordAmount;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public void addPayments(Payment pay1) {
        payments.add(pay1);
    }
}
