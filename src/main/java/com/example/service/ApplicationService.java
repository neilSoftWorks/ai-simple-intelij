package com.example.service;

import com.example.model.ApplicationStatus;
import com.example.model.BusinessDetails;
import com.example.repository.ApplicationStatusRepository;
import com.example.repository.BusinessDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationStatusRepository applicationStatusRepository;

    @Autowired
    private BusinessDetailsRepository businessDetailsRepository;

    // ... (KafkaProducerService and other dependencies)

    public ApplicationStatus submitApplication(BusinessDetails businessDetails) {
        businessDetails = businessDetailsRepository.save(businessDetails);
        ApplicationStatus applicationStatus = new ApplicationStatus();
        applicationStatus.setBusinessDetails(businessDetails); // Set relationship
        applicationStatus.setStatus("SUBMITTED");
        applicationStatus = applicationStatusRepository.save(applicationStatus);

        // ... (Kafka event publishing)
        return applicationStatus;
    }

    public List<BusinessDetails> getAllBusinessDetails() {
        return businessDetailsRepository.findAll();
    }

    public BusinessDetails getBusinessDetailsById(Long id) {
        Optional<BusinessDetails> optionalBusinessDetails = businessDetailsRepository.findById(id);
        return optionalBusinessDetails.orElse(null);
    }

    public BusinessDetails updateBusinessDetails(Long id, BusinessDetails businessDetails) {
        Optional<BusinessDetails> optionalBusinessDetails = businessDetailsRepository.findById(id);
        if (optionalBusinessDetails.isPresent()) {
            businessDetails.setId(id);
            return businessDetailsRepository.save(businessDetails);
        } else {
            return null;
        }
    }
}