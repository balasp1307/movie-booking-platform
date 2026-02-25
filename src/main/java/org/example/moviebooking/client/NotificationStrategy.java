package org.example.moviebooking.client;

public interface NotificationStrategy {
    void sendNotification(String recipient, String message);
}
