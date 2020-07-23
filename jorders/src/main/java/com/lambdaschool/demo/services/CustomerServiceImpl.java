package com.lambdaschool.demo.services;

import com.lambdaschool.demo.models.Agent;
import com.lambdaschool.demo.models.Customer;
import com.lambdaschool.demo.models.Order;
import com.lambdaschool.demo.models.Payment;
import com.lambdaschool.demo.repositories.AgentRepository;
import com.lambdaschool.demo.repositories.CustomerRepository;
import com.lambdaschool.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> allCust = new ArrayList<>();
        Iterable<Customer> existingCust = customerRepository.findAll();
        existingCust.iterator().forEachRemaining(allCust::add);
        return allCust;
    }

    @Override
    public Customer findCustomerById(long id) throws
            EntityNotFoundException
    {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer  " + id + " Not Found"));
    }

    @Override
    public List<Customer> findByPartOfName(String part) {
        return customerRepository.findByCustnameContainingIgnoringCase(part);
    }


    @Transactional
    @Override
    public void delete(long id)
    {
        if (customerRepository.findById(id)
                .isPresent())
        {
            customerRepository.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Customer " + id + " Not Found");
        }
    }
    
    /////////////crud
    

    @Transactional
    @Override
    public Customer save(Customer customer)//save and replace if found
    {//POST   ,   PUT
        Customer newCustomer = new Customer();

        if(customer.getAgent() != null) {
            Agent agent = agentRepository.findById(customer.getAgent()
                    .getAgentcode())
                    .orElseThrow(() -> new EntityNotFoundException("Agent " + customer.getAgent()
                            .getAgentcode() + " Not Found"));
            //You must add agent to customer in both cases either creating new customer or replacing an existing one and it will be added automatically to agent no need to add it explicitly (will produce error)
            newCustomer.setAgent(agent);
        }
        else{
            throw new EntityNotFoundException("Agent is not specefied inside received order JSON");
        }


        if (customer.getCustcode() != 0)
        {//replacing existing customer
            customerRepository.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found"));

            newCustomer.setCustcode(customer.getCustcode());

        }





        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setWorkingarea(customer.getWorkingarea());


        //note that existing order in the customer to be replaced will be deleted and new orders will be created with new ids

        newCustomer.getOrders().clear();
        for (Order o : customer.getOrders())
        {
            Order newOrder = new Order(o.getOrdamount(),
                    o.getAdvanceamount(),
                    newCustomer,
                    o.getOrderdescription());

            newOrder.getPayments().clear();
            Set<Payment> orderPayments = o.getPayments();
            orderPayments.iterator().forEachRemaining(p -> {
                Payment payment = paymentRepository.findById(p.getPaymentid())
                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
                newOrder.addPayments(payment);
            });


            newCustomer.getOrders()
                    .add(newOrder);
        }

        return customerRepository.save(newCustomer);
    }

    @Transactional
    @Override
    public Customer update( //update only assigned fields  //PATCH
            Customer customer,
            long id)
    {
        Customer currentCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));



        if(customer.getAgent() != null){
            Agent agent = agentRepository.findById(customer.getAgent().getAgentcode())
                    .orElseThrow(() -> new EntityNotFoundException("Agent " + customer.getAgent().getAgentcode() + " Not Found"));
            currentCustomer.setAgent(agent);
        }
        if (customer.getCustname() != null)
        {
            currentCustomer.setCustname(customer.getCustname());
        }

        if (customer.getCustcity() != null)
        {
            currentCustomer.setCustcity(customer.getCustcity());
        }

        if (customer.getCustcountry() != null)
        {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }

        if (customer.getGrade() != null) {
            currentCustomer.setGrade(customer.getGrade());
        }

        if (customer.getOpeningamt() != 0.0) {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }

        if (customer.getOutstandingamt() != 0.0){
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }

        if (customer.getPaymentamt() != 0.0) {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }

        if (customer.getPhone() != null) {
            currentCustomer.setPhone(customer.getPhone());
        }

        if (customer.getReceiveamt() != 0.0) {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }

        if (customer.getWorkingarea() != null) {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }


        if (customer.getOrders()
                .size() > 0)
        {

            //commented because we update but not replace
//            currentCustomer.getOrders()
//                    .clear();

            for (Order o : customer.getOrders())
            {
                Order newOrder = new Order(o.getOrdamount(),
                    o.getAdvanceamount(),
                    currentCustomer,
                    o.getOrderdescription());

                newOrder.getPayments().clear();
                Set<Payment> orderPayments = o.getPayments();
                orderPayments.iterator().forEachRemaining(p -> {
                    Payment payment = paymentRepository.findById(p.getPaymentid())
                            .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
                    newOrder.addPayments(payment);
                });

                currentCustomer.getOrders()
                        .add(newOrder);
            }
        }

        return customerRepository.save(currentCustomer);
    }


}
