package com.ionmind.fittracker_spring.mapper;

import com.ionmind.fittracker_spring.dto.response.UserResponse;
import com.ionmind.fittracker_spring.model.entity.User;

public class UserMapper {
    
    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
