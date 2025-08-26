package com.example.agent_management.DTOs;

import java.time.LocalDateTime;
public record UserInfoResponse(
        String status,
        LocalDateTime deleteDate,
        boolean adminUser,
        boolean emailPecVerified,
        boolean temporalPassword
) {}
