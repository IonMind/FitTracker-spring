package com.ionmind.fittracker_spring.dto.response;

import com.ionmind.fittracker_spring.model.enums.ExerciseCategory;
import com.ionmind.fittracker_spring.model.enums.MuscleGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseResponse {
    private Long id;
    private String name;
    private String description;
    private ExerciseCategory category;
    private MuscleGroup muscleGroup;
    private LocalDateTime createdAt;
}
