package com.ionmind.fittracker_spring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutExerciseResponse {
    private Long id;
    private ExerciseResponse exercise;
    private Integer sets;
    private Integer repetitions;
    private BigDecimal weight;
    private Integer durationMinutes;
    private Integer orderIndex;
}
