package com.example.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ApplicationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status = "SUBMITTED";
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne  // This is the correct way to map the relationship
    @JoinColumn(name = "business_id") // Maps to the business_id column
    private BusinessDetails businessDetails;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public BusinessDetails getBusinessDetails() { return businessDetails; }
    public void setBusinessDetails(BusinessDetails businessDetails) { this.businessDetails = businessDetails; }
}