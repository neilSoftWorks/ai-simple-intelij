package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.ApplicationStatus;
import com.example.model.BusinessDetails;
import com.example.service.ApplicationService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<?> submitApplication(@RequestBody BusinessDetails businessDetails) {
        ApplicationStatus status = applicationService.submitApplication(businessDetails);
        return ResponseEntity.ok(status);
    }

    @GetMapping
    public ResponseEntity<List<BusinessDetails>> getAllBusinessDetails() {
        List<BusinessDetails> businessDetails = applicationService.getAllBusinessDetails();
        return ResponseEntity.ok(businessDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessDetails> getBusinessDetailsById(@PathVariable Long id) {
        BusinessDetails businessDetails = applicationService.getBusinessDetailsById(id);
        if (businessDetails != null) {
            return ResponseEntity.ok(businessDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}") // Add this method and annotation
    @CrossOrigin(origins = "*") // Add this annotation
    public ResponseEntity<BusinessDetails> updateBusinessDetails(@PathVariable Long id, @RequestBody BusinessDetails businessDetails) {
        BusinessDetails updatedBusinessDetails = applicationService.updateBusinessDetails(id, businessDetails);
        if (updatedBusinessDetails != null) {
            return ResponseEntity.ok(updatedBusinessDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private DataSource dataSource;

    @GetMapping("/testdb")
    public String testDatabaseConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            return "Database connection successful!";
        } catch (SQLException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            return "Database connection failed: " + stackTrace;
        }
    }
}