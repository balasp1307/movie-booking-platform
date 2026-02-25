package org.example.moviebooking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final org.example.moviebooking.service.PaymentService paymentService;

    @PostMapping
    public String pay(@RequestParam BigDecimal amount) {
        return paymentService.pay(amount);
    }
}