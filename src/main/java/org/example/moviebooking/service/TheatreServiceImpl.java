package org.example.moviebooking.service;

import lombok.RequiredArgsConstructor;
import org.example.moviebooking.domain.Theatre;
import org.example.moviebooking.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository repository;

    @Override
    public Theatre onboard(Theatre theatre) {
        theatre.setActive(true);
        theatre.setCreatedAt(LocalDateTime.now());
        return repository.save(theatre);
    }

    @Override
    public Theatre update(Long id, Theatre theatre) {
        Theatre existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));

        existing.setTheatreName(theatre.getTheatreName());
        existing.setCity(theatre.getCity());
        existing.setAddress(theatre.getAddress());
        existing.setTotalScreens(theatre.getTotalScreens());

        return repository.save(existing);
    }

    @Override
    public List<Theatre> getActiveTheatresByCity(String city) {
        return repository.findByCityAndActiveTrue(city);
    }

    @Override
    public Theatre getActiveTheatresByPartnerEmailAndCity(String partnerEmail, String city) {
        return repository.findByPartnerEmailAndCityAndActiveTrue(partnerEmail, city);
    }

}
