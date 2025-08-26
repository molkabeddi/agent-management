package com.example.agent_management.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record AgentRequest(
        String firstName,
        String lastName,
        String email,
        Boolean emailVerified,
        Set<String> roles,
        Boolean active,
        LocalDate dateOfBirth,
        LocalDateTime lastLogin,
        // Adresse
        String country,
        String state,
        String addressLine,
        String zipCode,
        // UserInfo
        LocalDateTime deleteDate,
        Boolean temporalPassword
) {}
