package com.example.controller;

import com.example.model.BusinessDetails;
import com.example.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:8080")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public BusinessDetails submitApplication(@RequestBody BusinessDetails businessDetails) {
        return applicationService.submitApplication(businessDetails);
    }

    @GetMapping
    public List<BusinessDetails> getAllBusinessDetails() {
        return applicationService.getAllBusinessDetails();
    }

    @GetMapping("/{id}")
    public Optional<BusinessDetails> getBusinessDetailsById(@PathVariable Long id) {
        return applicationService.getBusinessDetailsById(id);
    }

    @PutMapping("/{id}")
    public BusinessDetails updateBusinessDetails(@PathVariable Long id, @RequestBody BusinessDetails updatedDetails) {
        return applicationService.updateBusinessDetails(id, updatedDetails);
    }

    // Other endpoints...
}
