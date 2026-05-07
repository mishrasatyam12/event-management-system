package com.mishra.event_management_system.controller;

import com.mishra.event_management_system.model.Event;
import com.mishra.event_management_system.model.User;
import com.mishra.event_management_system.service.EventService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZER') or hasAuthority('ADMIN')")
    public Event createEvent(@RequestBody Event event, @AuthenticationPrincipal User user){
        return eventService.createEvent(event,user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZER') or hasAuthority('ADMIN')")
    public Event updateEvent(@PathVariable Long id,@RequestBody Event event){
        event.setId(id);
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZER') or hasAuthority('ADMIN')")
    public String deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return "Event deleted sucessfully";
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id){
        return eventService.getEvent(id);
    }

    @GetMapping
    public Page<Event> getAllEvents(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size){
        return eventService.getAllEvents(PageRequest.of(page,size));
    }

    @GetMapping("/search")
    public Page<Event> searchEvents(@RequestParam String keyword,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size){
        return eventService.searchEvents(keyword,PageRequest.of(page,size));
    }
}
