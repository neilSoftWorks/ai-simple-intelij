package com.example.service;

import com.example.model.BusinessDetails;
import com.example.repository.BusinessDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApplicationServiceTest {

    @Mock
    private BusinessDetailsRepository businessDetailsRepository;

    @InjectMocks
    private ApplicationService applicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void submitApplication_shouldSaveBusinessDetails() {
        BusinessDetails businessDetails = new BusinessDetails();
        when(businessDetailsRepository.save(businessDetails)).thenReturn(businessDetails);

        BusinessDetails result = applicationService.submitApplication(businessDetails);

        assertNotNull(result);
        verify(businessDetailsRepository, times(1)).save(businessDetails);
    }

    @Test
    void getAllBusinessDetails_shouldReturnListOfBusinessDetails() {
        BusinessDetails business1 = new BusinessDetails();
        BusinessDetails business2 = new BusinessDetails();
        List<BusinessDetails> businessList = Arrays.asList(business1, business2);
        when(businessDetailsRepository.findAll()).thenReturn(businessList);

        List<BusinessDetails> result = applicationService.getAllBusinessDetails();

        assertEquals(2, result.size());
        verify(businessDetailsRepository, times(1)).findAll();
    }

    @Test
    void getBusinessDetailsById_shouldReturnBusinessDetails() {
        Long id = 1L;
        BusinessDetails businessDetails = new BusinessDetails();
        when(businessDetailsRepository.findById(id)).thenReturn(Optional.of(businessDetails));

        Optional<BusinessDetails> result = applicationService.getBusinessDetailsById(id);

        assertTrue(result.isPresent());
        verify(businessDetailsRepository, times(1)).findById(id);
    }

    @Test
    void updateBusinessDetails_shouldUpdateAndSaveBusinessDetails() {
        Long id = 1L;
        BusinessDetails existingDetails = new BusinessDetails();
        existingDetails.setId(id);
        BusinessDetails updatedDetails = new BusinessDetails();
        updatedDetails.setName("Updated Name");

        when(businessDetailsRepository.findById(id)).thenReturn(Optional.of(existingDetails));
        when(businessDetailsRepository.save(any(BusinessDetails.class))).thenReturn(existingDetails);

        BusinessDetails result = applicationService.updateBusinessDetails(id, updatedDetails);

        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        verify(businessDetailsRepository, times(1)).save(any(BusinessDetails.class));
    }
}
