package com.lambdaschool.demo.services;

import com.lambdaschool.demo.models.Agent;
import com.lambdaschool.demo.models.Customer;
import com.lambdaschool.demo.models.Order;
import com.lambdaschool.demo.models.Payment;
import com.lambdaschool.demo.repositories.CustomerRepository;
import com.lambdaschool.demo.repositories.OrderRepository;
import com.lambdaschool.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

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
        orderRepository.findByAdvanceamountGreaterThan(0.0).iterator().forEachRemaining(orders::add);
        return orders;
    }

    //************************//
    //  CRUD                  //
    //************************//

    @Transactional
    @Override
    public void delete(long num)
    {
        if (orderRepository.findById(num)
                .isPresent())
        {
            orderRepository.deleteById(num);
        } else
        {
            throw new EntityNotFoundException("Order " + num + " Not Found");
        }
    }

    @Transactional
    @Override
    public Order save(Order order) //POST  ,  PUT
    {
        Order newOrder = new Order();
        //Check if the customer inside received order exists or not
        if(order.getCustomer() != null){
            Customer presistedCustomer = customerRepository.findById(order.getCustomer().getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + order.getCustomer().getCustcode() + " Not Found"));

            newOrder.setCustomer(presistedCustomer);
        }
        else{
            throw new EntityNotFoundException("Customer is not specefied inside received order JSON");
        }
        if (order.getOrdnum() != 0)//new order to existing customer
        {
            orderRepository.findById(order.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " Not Found"));
            newOrder.setOrdnum(order.getOrdnum());
            
        }
        else{//replace existing order
        }

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());



        newOrder.getPayments().clear();
        Set<Payment> orderPayments = order.getPayments();
        orderPayments.iterator().forEachRemaining(p -> {
            Payment payment = paymentRepository.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
            newOrder.addPayments(payment);
        });



        return orderRepository.save(newOrder);
    }

//    @Transactional
//    @Override
//    public Order update(
//            Order order,
//            long id)
//    {
//        Order currentOrder = orderRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found"));
//
//        if (order.getName() != null)
//        {
//            currentOrder.setName(order.getName());
//        }
//
//        if (order.getAddress() != null)
//        {
//            currentOrder.setAddress(order.getAddress());
//        }
//
//        if (order.getCity() != null)
//        {
//            currentOrder.setCity(order.getCity());
//        }
//
//        if (order.getState() != null)
//        {
//            currentOrder.setState(order.getState());
//        }
//
//        if (order.getTelephone() != null)
//        {
//            currentOrder.setTelephone(order.getTelephone());
//        }
//
//        if (order.getPayments()
//                .size() > 0)
//        {
//            currentOrder.getPayments()
//                    .clear();
//            for (Payment p : order.getPayments())
//            {
//                Payment newPay = paymentrepos.findById(p.getPaymentid())
//                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
//
//                currentOrder.getPayments().add(newPay);
//            }
//        }
//
//        if (order.hasvalueforseatcapacity)
//        {
//            currentOrder.setSeatcapacity(order.getSeatcapacity());
//        }
//
//        if (order.getMenus()
//                .size() > 0)
//        {
//            currentOrder.getMenus()
//                    .clear();
//            for (Menu m : order.getMenus())
//            {
//                Menu newMenu = new Menu(m.getDish(),
//                        m.getPrice(),
//                        currentOrder);
//
//                currentOrder.getMenus()
//                        .add(newMenu);
//            }
//        }
//
//        return restrepos.save(currentOrder);
//    }
}
