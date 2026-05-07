package com.mishra.event_management_system.service;

import com.mishra.event_management_system.model.User;
import com.mishra.event_management_system.repository.UserRepository;
import com.mishra.event_management_system.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private UserRepository repo;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;




    public String login(String email, String password){
        User user=repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }
        return jwtUtil.generateTokens(user.getEmail());
    }

}
