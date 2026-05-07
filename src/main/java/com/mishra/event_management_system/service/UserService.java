package com.mishra.event_management_system.service;

import com.mishra.event_management_system.model.User;
import com.mishra.event_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    private UserRepository repo;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user){
        if(user.getPassword() != null){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return repo.save(user);
    }


}
