package com.example.agent_management.repositories;
import com.example.agent_management.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findByUserId(String userId);
    boolean existsByEmail(String email);
}

