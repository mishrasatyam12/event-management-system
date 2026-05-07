package com.mishra.event_management_system.repository;

import com.mishra.event_management_system.model.Booking;
import com.mishra.event_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByUser(User user);
}
