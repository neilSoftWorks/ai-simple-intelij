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
                    businessDetails.setName(updatedDetails.getName());
                    businessDetails.setContactDetails(updatedDetails.getContactDetails());
                    businessDetails.setAddress(updatedDetails.getAddress());
                    businessDetails.setIndustry(updatedDetails.getIndustry());
                    businessDetails.setFinancialInformation(updatedDetails.getFinancialInformation());
                    businessDetails.setPhoneNumber(updatedDetails.getPhoneNumber());
                    businessDetails.setEmailAddress(updatedDetails.getEmailAddress());
                    return businessDetailsRepository.save(businessDetails);
                })
                .orElse(null);
    }
}
