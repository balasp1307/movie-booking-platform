package org.example.moviebooking.client;

import org.springframework.stereotype.Component;

@Component("WHATSAPP")
public class WhatsAppNotification implements NotificationStrategy {

    @Override
    public void sendNotification(String recipient, String message) {
        // TODO: Simulate sending an whatsApp notification via WhatsApp service provider API
        System.out.println("Sending WhatsApp notification to " + recipient + ": " + message);
    }
}
