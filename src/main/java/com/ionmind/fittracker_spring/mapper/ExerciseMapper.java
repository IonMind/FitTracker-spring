package com.ionmind.fittracker_spring.mapper;

import com.ionmind.fittracker_spring.dto.response.ExerciseResponse;
import com.ionmind.fittracker_spring.model.entity.Exercise;

public class ExerciseMapper {
    
    public static ExerciseResponse toResponse(Exercise exercise) {
        return ExerciseResponse.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .description(exercise.getDescription())
                .category(exercise.getCategory())
                .muscleGroup(exercise.getMuscleGroup())
                .createdAt(exercise.getCreatedAt())
                .build();
    }
}
