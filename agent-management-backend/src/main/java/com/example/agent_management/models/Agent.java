package com.example.agent_management.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "agents", indexes = {
        @Index(name = "idx_agents_user_id", columnList = "user_id", unique = true),
        @Index(name = "idx_agents_email", columnList = "email", unique = true)
})
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true, length = 7)
    private String userId;

    @Column(name = "first_name", nullable = false, length = 60)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 60)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 180)
    private String email;

    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified = false;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "agent_roles", joinColumns = @JoinColumn(name = "agent_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 50)
    private Set<Role> roles = new HashSet<>();

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    // ---- Adresse ----
    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "state", length = 120)
    private String state;

    @Column(name = "address_line", length = 255)
    private String addressLine;

    @Column(name = "zip_code", length = 20)
    private String zipCode;

    // ---- UserInfo ----
    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "temporal_password", nullable = false)
    private boolean temporalPassword = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Agent() {
    }

    // Getters / Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isEmailVerified() { return emailVerified; }
    public void setEmailVerified(boolean emailVerified) { this.emailVerified = emailVerified; }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public LocalDateTime getLastLogin() { return lastLogin; }
    public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getAddressLine() { return addressLine; }
    public void setAddressLine(String addressLine) { this.addressLine = addressLine; }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public LocalDateTime getDeleteDate() { return deleteDate; }
    public void setDeleteDate(LocalDateTime deleteDate) { this.deleteDate = deleteDate; }

    public boolean isTemporalPassword() { return temporalPassword; }
    public void setTemporalPassword(boolean temporalPassword) { this.temporalPassword = temporalPassword; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Transient
    public String getFullname() {
        String fn = firstName == null ? "" : firstName;
        String ln = lastName == null ? "" : lastName;
        return (fn + " " + ln).trim();
    }
}
