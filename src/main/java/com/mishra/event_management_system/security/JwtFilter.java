package com.mishra.event_management_system.security;

import com.mishra.event_management_system.model.User;
import com.mishra.event_management_system.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");

        String email=null;
        String token=null;

        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token= authHeader.substring(7);
            try{
                email = jwtUtil.extractEmail(token);
            }catch(ExpiredJwtException e){
                System.out.println("JWT Expired");
            } catch (Exception e) {
                System.out.println("Invalid token");
            }
        }
        if(email!= null && SecurityContextHolder.getContext().getAuthentication()==null){
            User user= userRepository.findByEmail(email).orElse(null);
            if(user != null && jwtUtil.validateToken(token)){
                UsernamePasswordAuthenticationToken authToken=
                        new UsernamePasswordAuthenticationToken(user,null,null);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);

    }
}
