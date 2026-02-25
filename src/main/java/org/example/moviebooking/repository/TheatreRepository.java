package org.example.moviebooking.repository;

import org.example.moviebooking.domain.Theatre;

import java.util.List;

public interface TheatreRepository extends  org.springframework.data.jpa.repository.JpaRepository<org.example.moviebooking.domain.Theatre, Long> {
    Theatre findByPartnerEmailAndCityAndActiveTrue(String partnerEmail, String city);

    List<Theatre> findByCityAndActiveTrue(String city);

}
