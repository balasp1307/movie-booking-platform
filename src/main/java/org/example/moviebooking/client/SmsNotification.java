package org.example.moviebooking.client;

import org.springframework.stereotype.Component;

@Component("SMS")
public class SmsNotification implements NotificationStrategy {

    @Override
    public void sendNotification(String recipient, String message) {
    // TODO: Simulate sending an SMS notification via SMS service provider API
    System.out.println("Sending SMS notification to " + recipient + ": " + message);
    }

}
