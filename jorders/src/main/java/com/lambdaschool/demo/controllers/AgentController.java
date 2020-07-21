package com.lambdaschool.demo.controllers;

import com.lambdaschool.demo.models.Agent;
import com.lambdaschool.demo.models.Customer;
import com.lambdaschool.demo.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agents")
public class AgentController {
    @Autowired
    private AgentService agentService;

    @GetMapping(value = "/agent/{code}",
            produces = "application/json")
    public ResponseEntity<?> getAgentByCode
            (@PathVariable Long code)
    {
        Agent agent = agentService.findAgentByCode(code);
        return new ResponseEntity<>(agent,
                HttpStatus.OK);
    }
}
