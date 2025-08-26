package com.example.agent_management.Mappers;

import com.example.agent_management.DTOs.AgentRequest;
import com.example.agent_management.DTOs.AgentResponse;
import com.example.agent_management.DTOs.UserAddressResponse;
import com.example.agent_management.DTOs.UserInfoResponse;
import com.example.agent_management.models.Agent;
import com.example.agent_management.models.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class AgentMapper {

    private AgentMapper() {}

    // ----------- Create -----------
    public static Agent toEntityForCreate(AgentRequest req, String generatedUserId) {
        Set<Role> roles = (req.roles() == null || req.roles().isEmpty())
                ? Set.of(Role.ROLE_USER)
                : req.roles().stream()
                .map(String::trim).map(String::toUpperCase).map(Role::valueOf)
                .collect(Collectors.toSet());

        Agent agent = new Agent();
        agent.setUserId(generatedUserId);
        agent.setFirstName(req.firstName());
        agent.setLastName(req.lastName());
        agent.setEmail(req.email());
        agent.setEmailVerified(Boolean.TRUE.equals(req.emailVerified()));
        agent.setRoles(new HashSet<>(roles));
        agent.setActive(req.active() == null ? true : req.active());
        agent.setDateOfBirth(req.dateOfBirth());
        agent.setLastLogin(req.lastLogin());

        // Adresse
        agent.setCountry(req.country());
        agent.setState(req.state());
        agent.setAddressLine(req.addressLine());
        agent.setZipCode(req.zipCode());

        // UserInfo
        agent.setDeleteDate(req.deleteDate());
        agent.setTemporalPassword(Boolean.TRUE.equals(req.temporalPassword())); // ✅ ajouté

        return agent;
    }

    // ----------- Update -----------
    public static void copyForUpdate(Agent agent, AgentRequest req) {
        agent.setFirstName(req.firstName());
        agent.setLastName(req.lastName());
        agent.setEmail(req.email());
        agent.setEmailVerified(Boolean.TRUE.equals(req.emailVerified()));

        if (req.roles() != null && !req.roles().isEmpty()) {
            Set<Role> roles = req.roles().stream()
                    .map(String::trim).map(String::toUpperCase).map(Role::valueOf)
                    .collect(Collectors.toSet());
            agent.setRoles(roles);
        }

        if (req.active() != null) agent.setActive(req.active());
        agent.setDateOfBirth(req.dateOfBirth());
        agent.setLastLogin(req.lastLogin());

        // Adresse
        agent.setCountry(req.country());
        agent.setState(req.state());
        agent.setAddressLine(req.addressLine());
        agent.setZipCode(req.zipCode());

        // UserInfo
        agent.setDeleteDate(req.deleteDate());
        if (req.temporalPassword() != null) {
            agent.setTemporalPassword(req.temporalPassword()); // ✅ ajouté
        }
    }

    // ----------- Response -----------
    public static AgentResponse toResponse(Agent a) {
        Set<String> roles = a.getRoles() == null ? Set.of()
                : a.getRoles().stream().map(Enum::name).collect(Collectors.toSet());

        UserInfoResponse ui = new UserInfoResponse(
                a.isActive() ? "active" : "inactive",   // dérivé
                a.getDeleteDate(),                       // persisté
                roles.contains("ROLE_ADMIN"),            // dérivé
                a.isEmailVerified(),                     // persisté
                a.isTemporalPassword()                   // ✅ persisté
        );

        UserAddressResponse addr = new UserAddressResponse(
                a.getCountry(),
                a.getState(),
                a.getAddressLine(),
                a.getZipCode()
        );

        return new AgentResponse(
                a.getId(),
                a.getUserId(),
                a.getFirstName(),
                a.getLastName(),
                a.getFullname(),
                a.getEmail(),
                a.isEmailVerified(),
                roles,
                a.isActive(),
                a.getDateOfBirth(),
                a.getLastLogin(),
                a.getCreatedAt(),
                a.getUpdatedAt(),
                ui,
                addr
        );
    }
}
