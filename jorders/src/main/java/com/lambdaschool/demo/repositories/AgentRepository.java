package com.lambdaschool.demo.repositories;

import com.lambdaschool.demo.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<Agent, Long> {
    Agent findByAgentname(String agentname);


}
