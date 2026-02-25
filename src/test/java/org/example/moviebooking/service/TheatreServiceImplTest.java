package org.example.moviebooking.service;

import org.example.moviebooking.domain.Theatre;
import org.example.moviebooking.repository.TheatreRepository;
import org.example.moviebooking.service.TheatreServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TheatreServiceImplTest {

    @Mock
    private TheatreRepository repository;

    @InjectMocks
    private TheatreServiceImpl service;

    @Test
    public void onboard_happyPath() {

        Theatre theatre = Theatre.builder()
                .theatreName("PVR")
                .city("Bangalore")
                .build();

        when(repository.save(any())).thenReturn(theatre);

        Theatre saved = service.onboard(theatre);

        assertThat(saved.getActive()).isTrue();
    }

    @Test
    public void update_notFound_shouldThrow() {

        when(repository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.update(Long.valueOf(1L), new Theatre()))
                .isInstanceOf(RuntimeException.class);
    }
}
