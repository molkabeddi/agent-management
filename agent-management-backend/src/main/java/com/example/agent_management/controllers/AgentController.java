package com.example.agent_management.controllers;

import com.example.agent_management.DTOs.AgentRequest;
import com.example.agent_management.DTOs.AgentResponse;
import com.example.agent_management.services.AgentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping("/agents")
    public List<AgentResponse> list() {
        return agentService.findAll();
    }

    @PostMapping("/agent")
    @ResponseStatus(HttpStatus.CREATED)
    public AgentResponse create(@Valid @RequestBody AgentRequest req) {
        return agentService.create(req);
    }

    @PutMapping("/{userId}")
    public AgentResponse update(@PathVariable String userId,
                                @Valid @RequestBody AgentRequest req) {
        return agentService.update(userId, req);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String userId) {
        agentService.delete(userId);
    }
}
