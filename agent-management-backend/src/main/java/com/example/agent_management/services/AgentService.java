package com.example.agent_management.services;

import com.example.agent_management.DTOs.AgentRequest;
import com.example.agent_management.DTOs.AgentResponse;
import com.example.agent_management.Mappers.AgentMapper;
import com.example.agent_management.models.Agent;
import com.example.agent_management.repositories.AgentRepository;
import com.example.agent_management.Utils.UserIdGenerator;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgentService {

    private final AgentRepository agentRepository;

    // ✅ Un seul constructeur pour l'injection
    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Transactional(readOnly = true)
    public List<AgentResponse> findAll() {
        return agentRepository.findAll().stream()
                .map(AgentMapper::toResponse)
                .toList();
    }

    @Transactional
    public AgentResponse create(AgentRequest req) {
        if (agentRepository.existsByEmail(req.email())) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        String userId = UserIdGenerator.generate();
        Agent saved = agentRepository.save(AgentMapper.toEntityForCreate(req, userId));
        return AgentMapper.toResponse(saved);
    }

    @Transactional
    public AgentResponse update(String userId, AgentRequest req) {
        Agent agent = agentRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Agent not found: " + userId));

        if (!agent.getEmail().equals(req.email()) && agentRepository.existsByEmail(req.email())) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        // copie et sauvegarde
        AgentMapper.copyForUpdate(agent, req);
        return AgentMapper.toResponse(agentRepository.save(agent));
    }

    @Transactional
    public void delete(String userId) {
        Agent agent = agentRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Agent not found: " + userId));
        agentRepository.delete(agent);
    }
}
