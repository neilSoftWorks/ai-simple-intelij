package com.example.service;

import com.example.model.BusinessDetails;
import com.example.repository.BusinessDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private BusinessDetailsRepository businessDetailsRepository;

    public BusinessDetails submitApplication(BusinessDetails businessDetails) {
        return businessDetailsRepository.save(businessDetails);
    }

    public List<BusinessDetails> getAllBusinessDetails() {
        return businessDetailsRepository.findAll();
    }

    public Optional<BusinessDetails> getBusinessDetailsById(Long id) {
        return businessDetailsRepository.findById(id);
    }

    public BusinessDetails updateBusinessDetails(Long id, BusinessDetails updatedDetails) {
        return businessDetailsRepository.findById(id)
                .map(businessDetails -> {
                    businessDetails.setBusinessName(updatedDetails.getBusinessName());
                    businessDetails.setOwnerName(updatedDetails.getOwnerName());
                    businessDetails.setPhoneNumber(updatedDetails.getPhoneNumber()); // Added line
                    return businessDetailsRepository.save(businessDetails);
                })
                .orElse(null);
    }

    // Other methods...
}
