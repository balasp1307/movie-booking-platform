package org.example.moviebooking.factory;

import org.example.moviebooking.client.GmailNotification;
import org.example.moviebooking.client.NotificationStrategy;
import org.example.moviebooking.client.WhatsAppNotification;
import org.example.moviebooking.factory.NotificationFactory;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class NotificationFactoryTest {

    @Test
    void shouldReturnCorrectStrategy() {

        GmailNotification gmail = new GmailNotification();
        WhatsAppNotification whatsapp = new WhatsAppNotification();

        NotificationFactory factory =
                new NotificationFactory(Map.of("EMAIL", gmail, "WHATSAPP", whatsapp));

        NotificationStrategy strategy = factory.getSender("EMAIL");

        assertThat(strategy).isNotNull();
    }
}
