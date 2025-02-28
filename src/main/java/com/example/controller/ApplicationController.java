package com.example.controller;

import com.example.model.BusinessDetails;
import com.example.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<BusinessDetails> submitApplication(@Valid @RequestBody BusinessDetails businessDetails) {
        BusinessDetails submittedDetails = applicationService.submitApplication(businessDetails);
        return new ResponseEntity<>(submittedDetails, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BusinessDetails>> getAllBusinessDetails() {
        List<BusinessDetails> details = applicationService.getAllBusinessDetails();
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessDetails> getBusinessDetailsById(@PathVariable Long id) {
        Optional<BusinessDetails> detail = applicationService.getBusinessDetailsById(id);
        return detail.map(businessDetails -> new ResponseEntity<>(businessDetails, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessDetails> updateBusinessDetails(@PathVariable Long id, @Valid @RequestBody BusinessDetails updatedDetails) {
        BusinessDetails updatedBusinessDetails = applicationService.updateBusinessDetails(id, updatedDetails);
        if (updatedBusinessDetails != null) {
            return new ResponseEntity<>(updatedBusinessDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusinessDetails(@PathVariable Long id) {
        applicationService.deleteBusinessDetails(id); // Corrected line
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
