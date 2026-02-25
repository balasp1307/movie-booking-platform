package org.example.moviebooking.controller;

import org.example.moviebooking.service.BookingService;
import org.example.moviebooking.service.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Test
    void bookTickets_happyPath() throws Exception {

        when(bookingService.bookTickets(any()))
                .thenReturn("Booking Successful with ID: 1");

        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "movieId": "MOV1",
                          "theatreId": "T1",
                          "theatreName": "PVR",
                          "showId": "S1",
                          "showTime": "2026-02-28T14:30:00",
                          "seatNumbers": ["A1","A2"],
                          "customerEmail": "test@test.com",
                          "city": "Bangalore"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Booking Successful")));
    }

    @Test
    void bookTickets_invalidRequest_shouldReturnBadRequest() throws Exception {

        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}
