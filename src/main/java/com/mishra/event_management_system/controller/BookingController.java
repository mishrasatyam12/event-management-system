package com.mishra.event_management_system.controller;

import com.mishra.event_management_system.model.Booking;
import com.mishra.event_management_system.model.Event;
import com.mishra.event_management_system.model.User;
import com.mishra.event_management_system.service.BookingService;
import com.mishra.event_management_system.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final EventService eventService;

    @PostMapping("/{eventId}")
    @PreAuthorize("hasAuthority('USER')")
    public Booking bookEvent(@PathVariable Long eventId, @AuthenticationPrincipal User user){
        Event event=eventService.getEvent(eventId);
        return bookingService.bookEvent(user,event);
    }

    @DeleteMapping("/{bookingId}")
    @PreAuthorize("hasAuthority('USER')")
    public String cancelBooking(@PathVariable Long bookingId){
        bookingService.cancelBooking(bookingId);
        return "Booking cancelled successfully";
    }

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('USER')")
    public List<Booking> getMyBookings(@AuthenticationPrincipal User user){
        return bookingService.getUserBookings(user);
    }
}
