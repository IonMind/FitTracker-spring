package com.ionmind.fittracker_spring.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutExerciseRequest {
    
    @NotNull(message = "Exercise ID is required")
    private Long exerciseId;
    
    @NotNull(message = "Sets is required")
    @Min(value = 1, message = "Sets must be at least 1")
    private Integer sets;
    
    @NotNull(message = "Repetitions is required")
    @Min(value = 1, message = "Repetitions must be at least 1")
    private Integer repetitions;
    
    @Min(value = 0, message = "Weight must be non-negative")
    private BigDecimal weight;
    
    @Min(value = 0, message = "Duration must be non-negative")
    private Integer durationMinutes;
    
    @NotNull(message = "Order index is required")
    @Min(value = 0, message = "Order index must be non-negative")
    private Integer orderIndex;
}
