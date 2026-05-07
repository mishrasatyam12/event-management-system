package com.mishra.event_management_system.controller;

import com.mishra.event_management_system.model.Event;
import com.mishra.event_management_system.model.User;
import com.mishra.event_management_system.service.AuthService;
import com.mishra.event_management_system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    private AuthService authService;



    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return authService.login(user.getEmail(), user.getPassword());
    }



}
