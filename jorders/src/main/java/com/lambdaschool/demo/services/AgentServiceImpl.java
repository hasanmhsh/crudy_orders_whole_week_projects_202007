package com.lambdaschool.demo.services;

import com.lambdaschool.demo.models.Agent;
import com.lambdaschool.demo.models.Customer;
import com.lambdaschool.demo.repositories.AgentRepository;
import com.lambdaschool.demo.repositories.CustomerRepository;
import com.lambdaschool.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "agentService")
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Agent findAgentByCode(long code)  throws
            EntityNotFoundException
    {
        return agentRepository.findById(code)
                .orElseThrow(() -> new EntityNotFoundException("Agent  " + code + " Not Found"));
    }
    
    
//    //************************//
//    //  CRUD                  //
//    //************************//
//
//    @Transactional
//    @Override
//    public void delete(long code)
//    {
//        if (agentRepository.findById(code)
//                .isPresent())
//        {
//            agentRepository.deleteById(code);
//        } else
//        {
//            throw new EntityNotFoundException("Agent " + code + " Not Found");
//        }
//    }
//
//    @Transactional
//    @Override
//    public Agent save(Agent agent)
//    {
//        Agent newAgent = new Agent();
//
//        if (agent.getAgentCode() != 0)
//        {
//            agentRepository.findById(agent.getAgentCode())
//                    .orElseThrow(() -> new EntityNotFoundException("Agent " + agent.getAgentCode() + " Not Found"));
//
//            newAgent.setAgentCode(agent.getAgentCode());
//        }
//
//        newAgent.setAgentName(agent.getAgentName());
//        newAgent.setCommision(agent.getCommision());
//        newAgent.setCountry(agent.getCountry());
//        newAgent.setPhone(agent.getPhone());
//        newAgent.setWorkingArea(agent.getWorkingArea());
//
//        newAgent.getCustomers().clear();
//        for (Customer c : agent.getCustomers())
//        {
//            Customer newCustomer = paymentrepos.findById(p.getPaymentid())
//                    .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
//
//            newAgent.getPayments().add(newPay);
//        }
//
//        newAgent.getMenus()
//                .clear();
//        for (Menu m : agent.getMenus())
//        {
//            Menu newMenu = new Menu(m.getDish(),
//                    m.getPrice(),
//                    newAgent);
//
//            newAgent.getMenus()
//                    .add(newMenu);
//        }
//
//        return restrepos.save(newAgent);
//    }
//
//    @Transactional
//    @Override
//    public Agent update(
//            Agent agent,
//            long id)
//    {
//        Agent currentAgent = agentRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Agent " + id + " Not Found"));
//
//        if (agent.getName() != null)
//        {
//            currentAgent.setName(agent.getName());
//        }
//
//        if (agent.getAddress() != null)
//        {
//            currentAgent.setAddress(agent.getAddress());
//        }
//
//        if (agent.getCity() != null)
//        {
//            currentAgent.setCity(agent.getCity());
//        }
//
//        if (agent.getState() != null)
//        {
//            currentAgent.setState(agent.getState());
//        }
//
//        if (agent.getTelephone() != null)
//        {
//            currentAgent.setTelephone(agent.getTelephone());
//        }
//
//        if (agent.getPayments()
//                .size() > 0)
//        {
//            currentAgent.getPayments()
//                    .clear();
//            for (Payment p : agent.getPayments())
//            {
//                Payment newPay = paymentrepos.findById(p.getPaymentid())
//                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
//
//                currentAgent.getPayments().add(newPay);
//            }
//        }
//
//        if (agent.hasvalueforseatcapacity)
//        {
//            currentAgent.setSeatcapacity(agent.getSeatcapacity());
//        }
//
//        if (agent.getMenus()
//                .size() > 0)
//        {
//            currentAgent.getMenus()
//                    .clear();
//            for (Menu m : agent.getMenus())
//            {
//                Menu newMenu = new Menu(m.getDish(),
//                        m.getPrice(),
//                        currentAgent);
//
//                currentAgent.getMenus()
//                        .add(newMenu);
//            }
//        }
//
//        return restrepos.save(currentAgent);
//    }
    
}
