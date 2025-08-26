package com.example.agent_management.DTOs;

public record UserAddressResponse(
        String country,
        String state,
        String addressLine,
        String zipCode
) {}
