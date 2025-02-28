package com.example.controller;

import com.example.model.BusinessDetails;
import com.example.service.ApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ApplicationControllerTest {

    @Mock
    private ApplicationService applicationService;

    @InjectMocks
    private ApplicationController applicationController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(applicationController).setControllerAdvice(new ExceptionControllerAdvice()).build();
        objectMapper = new ObjectMapper();
    }

    // ... (rest of your test methods remain the same)
    @Test
    void submitApplication_shouldReturnBusinessDetails() throws Exception {
        BusinessDetails businessDetails = new BusinessDetails();
        when(applicationService.submitApplication(any(BusinessDetails.class))).thenReturn(businessDetails);

        mockMvc.perform(post("/api/applications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(businessDetails)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllBusinessDetails_shouldReturnListOfBusinessDetails() throws Exception {
        BusinessDetails business1 = new BusinessDetails();
        BusinessDetails business2 = new BusinessDetails();
        when(applicationService.getAllBusinessDetails()).thenReturn(Arrays.asList(business1, business2));

        mockMvc.perform(get("/api/applications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getBusinessDetailsById_shouldReturnBusinessDetails() throws Exception {
        Long id = 1L;
        BusinessDetails businessDetails = new BusinessDetails();
        when(applicationService.getBusinessDetailsById(id)).thenReturn(Optional.of(businessDetails));

        mockMvc.perform(get("/api/applications/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateBusinessDetails_shouldReturnUpdatedBusinessDetails() throws Exception {
        Long id = 1L;
        BusinessDetails businessDetails = new BusinessDetails();
        when(applicationService.updateBusinessDetails(eq(id), any(BusinessDetails.class))).thenReturn(businessDetails);

        mockMvc.perform(put("/api/applications/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(businessDetails)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteBusinessDetails_shouldReturnOk() throws Exception {
        Long id = 1L;
        doNothing().when(applicationService).deleteBusinessDetails(id);

        mockMvc.perform(delete("/api/applications/1"))
                .andExpect(status().isOk());
    }
}
