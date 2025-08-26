package com.example.agent_management.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record AgentResponse(
        Long id,
        String userId,
        String firstName,
        String lastName,
        String fullname,
        String email,
        boolean emailVerified,
        Set<String> roles,
        boolean active,
        LocalDate dateOfBirth,
        LocalDateTime lastLogin,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UserInfoResponse userInfo,
        UserAddressResponse userAddress
) {}
