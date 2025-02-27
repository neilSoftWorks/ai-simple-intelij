package com.example.controller;

import com.example.model.BusinessDetails;
import com.example.service.ApplicationService;
import com.example.repository.BusinessDetailsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = {"http://localhost:4200", "null"})
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private BusinessDetailsRepository businessDetailsRepository;

    @PostMapping
    public BusinessDetails submitApplication(@Valid @RequestBody BusinessDetails businessDetails) {
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

    @DeleteMapping("/{id}")
    public void deleteBusinessDetails(@PathVariable Long id) {
        businessDetailsRepository.deleteById(id);
    }
}
