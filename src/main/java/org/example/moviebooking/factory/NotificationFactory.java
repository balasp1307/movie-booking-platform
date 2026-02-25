package org.example.moviebooking.factory;

import org.example.moviebooking.client.NotificationStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificationFactory {

    private final Map<String, NotificationStrategy> senderMap;

    public NotificationFactory(Map<String, NotificationStrategy> senderMap) {
        this.senderMap = senderMap;
    }

    public NotificationStrategy getSender(String provider) {
        return senderMap.get(provider.toUpperCase());
    }
}
