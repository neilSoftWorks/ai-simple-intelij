package com.example.listener;

import com.example.event.BusinessDetailsCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BusinessDetailsCreatedListener {

    @EventListener
    public void handleBusinessDetailsCreated(BusinessDetailsCreatedEvent event) {
        // Perform actions based on the event
        System.out.println("EVENT - BusinessDetails created: " + event.getBusinessDetails().getId());
        // Add your custom logic here (e.g., send notifications, update other systems)
    }
}
