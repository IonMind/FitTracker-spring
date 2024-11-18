package com.ionmind.fittracker_spring.dto.request;

import com.ionmind.fittracker_spring.model.enums.WorkoutStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateWorkoutRequest {
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotBlank(message = "Workout name is required")
    @Size(max = 100, message = "Workout name must not exceed 100 characters")
    private String name;
    
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
    
    private LocalDateTime scheduledDate;
    
    @Builder.Default
    private WorkoutStatus status = WorkoutStatus.PENDING;
    
    private String comments;
    
    @Valid
    @NotNull(message = "Exercises list is required")
    @Size(min = 1, message = "At least one exercise is required")
    private List<WorkoutExerciseRequest> exercises;
}
