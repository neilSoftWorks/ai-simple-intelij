package com.example.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.ApplicationStatus;
import com.example.model.BusinessDetails;
import com.example.repository.ApplicationStatusRepository;
import com.example.repository.BusinessDetailsRepository;

@Service
public class ApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    private BusinessDetailsRepository businessDetailsRepository;

    @Autowired
    private ApplicationStatusRepository applicationStatusRepository;

    @Transactional
    public BusinessDetails submitApplication(BusinessDetails businessDetails) {
        BusinessDetails savedBusinessDetails = businessDetailsRepository.save(businessDetails);
        ApplicationStatus initialStatus = new ApplicationStatus();
        initialStatus.setBusinessDetails(savedBusinessDetails);
        initialStatus.setStatus("Submitted");
        applicationStatusRepository.save(initialStatus);
        return savedBusinessDetails;
    }

    public List<BusinessDetails> getAllBusinessDetails() {
        return businessDetailsRepository.findAll();
    }

    public Optional<BusinessDetails> getBusinessDetailsById(Long id) {
        return businessDetailsRepository.findById(id);
    }

    @Transactional
    public BusinessDetails updateBusinessDetails(Long id, BusinessDetails updatedBusinessDetails) {
        logger.info("Updating business details with id: {}", id);
        logger.info("Updated BusinessDetails: {}", updatedBusinessDetails);

        Optional<BusinessDetails> existingBusinessDetails = businessDetailsRepository.findById(id);
        if (existingBusinessDetails.isPresent()) {
            BusinessDetails businessDetails = existingBusinessDetails.get();
            businessDetails.setName(updatedBusinessDetails.getName());
            businessDetails.setAddress(updatedBusinessDetails.getAddress());
            businessDetails.setContact(updatedBusinessDetails.getContact());
            businessDetails.setPhone(updatedBusinessDetails.getPhone());
            businessDetails.setEmail(updatedBusinessDetails.getEmail());
            businessDetails.setIndustry(updatedBusinessDetails.getIndustry());

            BusinessDetails savedBusinessDetails = businessDetailsRepository.save(businessDetails);

            logger.info("Saved BusinessDetails: {}", savedBusinessDetails);
            return savedBusinessDetails;
        }
        return null;
    }

    @Transactional
    public void deleteBusinessDetails(Long id) {
        List<ApplicationStatus> applicationStatuses = applicationStatusRepository.findByBusinessDetailsId(id);
        applicationStatusRepository.deleteAll(applicationStatuses);
        businessDetailsRepository.deleteById(id);
    }
}
