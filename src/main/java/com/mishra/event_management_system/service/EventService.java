package com.mishra.event_management_system.service;

import com.mishra.event_management_system.model.Event;
import com.mishra.event_management_system.model.User;
import com.mishra.event_management_system.repository.EventRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event createEvent(Event event, User creator){
        event.setCreatedBy(creator);
        return eventRepository.save(event);
    }
    public Event updateEvent(Event event){
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }

    public Event getEvent(Long id){
        return eventRepository.findById(id).orElseThrow(()-> new RuntimeException("Event not found"));
    }

    public Page<Event> getAllEvents(Pageable pageable){
        return eventRepository.findAll(pageable);
    }

    public Page<Event> searchEvents(String keyword,Pageable pageable ){
        return (Page<Event>) eventRepository.findByTitleContainingIgnoringCase(keyword,pageable);
    }


}
