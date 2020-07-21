package com.lambdaschool.demo.services;

import com.lambdaschool.demo.models.Agent;

public interface AgentService {
    public Agent findAgentByCode(long code);
}
