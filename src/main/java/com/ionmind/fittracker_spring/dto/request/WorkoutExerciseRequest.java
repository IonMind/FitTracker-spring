package com.ionmind.fittracker_spring.dto.request;

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

    private Long exerciseId;

    private Integer sets;

    private Integer repetitions;

    private BigDecimal weight;

    private Integer durationMinutes;

    private Integer orderIndex;
}
