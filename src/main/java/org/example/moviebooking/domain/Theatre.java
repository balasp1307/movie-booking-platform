package org.example.moviebooking.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "theatres",
        indexes = {
                @Index(name = "idx_theatreName", columnList = "theatreName"),
                @Index(name = "idx_partner", columnList = "partnerEmail")
        })
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String theatreName;

    private String city;

    private String address;

    private Integer totalScreens;

    private String partnerEmail;

    private Boolean active;

    private LocalDateTime createdAt;
}
