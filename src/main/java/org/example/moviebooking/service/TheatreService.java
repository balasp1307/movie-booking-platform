package org.example.moviebooking.service;

import org.example.moviebooking.domain.Theatre;

import java.util.List;

public interface TheatreService {
    Theatre onboard(Theatre theatre);

    Theatre update(Long id, Theatre theatre);

    List<Theatre> getActiveTheatresByCity(String city);

    Theatre getActiveTheatresByPartnerEmailAndCity(String partneremail, String city);
}
