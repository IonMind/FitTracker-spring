package com.ionmind.fittracker_spring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    
    private String token;
    
    @Builder.Default
    private String type = "Bearer";
    
    private Long id;
    private String username;
    private String email;
    private String fullName;
    
    public AuthResponse(String token, Long id, String username, String email, String fullName) {
        this.token = token;
        this.type = "Bearer";
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
    }
}
