package org.example.moviebooking.controller;

import lombok.RequiredArgsConstructor;
import org.example.moviebooking.domain.Theatre;
import org.example.moviebooking.service.TheatreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatres")
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;

    @PostMapping
    public Theatre onboard(@RequestBody Theatre theatre) {
        return theatreService.onboard(theatre);
    }

    @PutMapping("/{id}")
    public Theatre update(@PathVariable Long id,
                          @RequestBody Theatre theatre) {
        return theatreService.update(id, theatre);
    }

    @GetMapping("/city/{city}")
    public Theatre getByCityAndPartnerEmail(@PathVariable String city, @RequestParam (required = true) String partnerEmail) {
        return theatreService.getActiveTheatresByPartnerEmailAndCity(partnerEmail, city);
    }
}
