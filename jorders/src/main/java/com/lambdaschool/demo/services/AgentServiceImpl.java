package com.lambdaschool.demo.services;

import com.lambdaschool.demo.models.Agent;
import com.lambdaschool.demo.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "agentService")
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentRepository agentRepository;

    @Override
    public Agent findAgentByCode(long code)  throws
            EntityNotFoundException
    {
        return agentRepository.findById(code)
                .orElseThrow(() -> new EntityNotFoundException("Agent  " + code + " Not Found"));
    }
}
