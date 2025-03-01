package com.example.listener;

import com.example.event.BusinessDetailsUpdatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BusinessDetailsUpdatedListener {

    @EventListener
    public void handleBusinessDetailsUpdated(BusinessDetailsUpdatedEvent event) {
        // Perform actions based on the event
        System.out.println("BusinessDetails updated: " + event.getBusinessDetails().getId());
        // Add your custom logic here (e.g., send notifications, update other systems)
    }
}
