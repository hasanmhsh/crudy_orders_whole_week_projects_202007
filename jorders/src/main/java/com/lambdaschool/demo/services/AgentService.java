package com.lambdaschool.demo.services;

import com.lambdaschool.demo.models.Agent;

public interface AgentService {
    public Agent findAgentByCode(long code);
    //crud
//    public void delete(long code);
//    public Agent save(Agent agent);
//    public Agent update(Agent agent, long code);
}
