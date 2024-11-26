package com.ionmind.fittracker_spring.config;

import com.ionmind.fittracker_spring.model.entity.User;
import com.ionmind.fittracker_spring.repository.UserRepository;
import com.ionmind.fittracker_spring.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceConfig {
    
    private final UserRepository userRepository;
    
    @Bean
    public UserDetailsService userDetailsService() {
        // Lambda expression implementation - replaces CustomUserDetailsService
        return username -> {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(
                            "User not found with username: " + username));
            
            return CustomUserDetails.build(user);
        };
    }
}
