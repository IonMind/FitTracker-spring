package com.ionmind.fittracker_spring.dto.request;

import com.ionmind.fittracker_spring.model.enums.WorkoutStatus;
import jakarta.validation.Valid;
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
public class UpdateWorkoutRequest {
    
    @Size(max = 100, message = "Workout name must not exceed 100 characters")
    private String name;
    
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
    
    private LocalDateTime scheduledDate;
    
    private WorkoutStatus status;
    
    private String comments;
    
    @Valid
    private List<WorkoutExerciseRequest> exercises;
}
