package com.mishra.event_management_system.repository;

import com.mishra.event_management_system.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
    Page<Event> findByTitleContainingIgnoringCase(String keyword, Pageable pageable);

}
