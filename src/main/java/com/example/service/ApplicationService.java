package com.example.service;

import com.example.event.BusinessDetailsCreatedEvent;
import com.example.event.BusinessDetailsUpdatedEvent;
import com.example.model.ApplicationStatus;
import com.example.model.BusinessDetails;
import com.example.repository.ApplicationStatusRepository;
import com.example.repository.BusinessDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private BusinessDetailsRepository businessDetailsRepository;

    @Autowired
    private ApplicationStatusRepository applicationStatusRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public BusinessDetails submitApplication(BusinessDetails businessDetails) {
        BusinessDetails savedBusinessDetails = businessDetailsRepository.save(businessDetails);
        ApplicationStatus initialStatus = new ApplicationStatus();
        initialStatus.setBusinessDetails(savedBusinessDetails);
        initialStatus.setStatus("Submitted");
        applicationStatusRepository.save(initialStatus);
        applicationEventPublisher.publishEvent(new BusinessDetailsCreatedEvent(this, savedBusinessDetails));
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
        Optional<BusinessDetails> existingBusinessDetails = businessDetailsRepository.findById(id);
        if (existingBusinessDetails.isPresent()) {
            updatedBusinessDetails.setId(id);
            BusinessDetails savedBusinessDetails = businessDetailsRepository.save(updatedBusinessDetails);
            ApplicationStatus updateStatus = new ApplicationStatus();
            updateStatus.setBusinessDetails(savedBusinessDetails);
            updateStatus.setStatus("Updated");
            applicationStatusRepository.save(updateStatus);

            // Publish event for update
            applicationEventPublisher.publishEvent(new BusinessDetailsUpdatedEvent(this, savedBusinessDetails));

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
