package org.example.moviebooking.client;

import org.example.moviebooking.enums.NotificationType;
import org.springframework.stereotype.Component;

@Component("GMAIL")
public class GmailNotification implements NotificationStrategy {

    @Override
    public void sendNotification(String recipient, String message) {
        // TODO: Simulate sending an email via Gmail API
        System.out.println("Sending Gmail notification to " + recipient + ": " + message);
    }
}
