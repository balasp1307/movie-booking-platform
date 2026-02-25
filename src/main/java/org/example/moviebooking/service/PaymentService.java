package org.example.moviebooking.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Simulates a call to an external payment gateway (e.g., PayPal).
     * Uses Resilience4j annotations for circuit breaking and retry logic.
     */
    @CircuitBreaker(name = "paypalService", fallbackMethod = "fallback")
    @Retry(name = "paypalRetry")
    public String pay(BigDecimal amount) {
        return restTemplate.postForObject(
                "https://api.sandbox.paypal.com/pay", // Mock URL for PayPal API
                amount,
                String.class);
    }

    public String fallback(BigDecimal amount, Throwable ex) {
        return "Payment service unavailable";
    }
}