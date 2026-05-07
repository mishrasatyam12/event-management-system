package com.mishra.event_management_system.service;


import com.mishra.event_management_system.model.Booking;
import com.mishra.event_management_system.model.Event;
import com.mishra.event_management_system.model.User;
import com.mishra.event_management_system.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking bookEvent(User user, Event event){
        Booking booking=Booking.builder()
                .user(user)
                .event(event)
                .bookingDate(LocalDateTime.now())
                .status(Booking.Status.CONFIRMED)
                .build();
        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long bookingId){
        Booking booking=bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(Booking.Status.CANCELLED);
        bookingRepository.save(booking);
    }

    public List<Booking> getUserBookings(User user){
        return bookingRepository.findByUser(user);
    }
}
