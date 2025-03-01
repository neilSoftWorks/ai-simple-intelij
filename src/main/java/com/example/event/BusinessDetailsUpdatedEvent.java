package com.example.event;

import com.example.model.BusinessDetails;
import org.springframework.context.ApplicationEvent;

public class BusinessDetailsUpdatedEvent extends ApplicationEvent {

    private final BusinessDetails businessDetails;

    public BusinessDetailsUpdatedEvent(Object source, BusinessDetails businessDetails) {
        super(source);
        this.businessDetails = businessDetails;
    }

    public BusinessDetails getBusinessDetails() {
        return businessDetails;
    }
}