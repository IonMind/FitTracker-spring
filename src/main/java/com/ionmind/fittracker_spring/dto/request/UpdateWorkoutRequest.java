package com.ionmind.fittracker_spring.dto.request;

import com.ionmind.fittracker_spring.model.enums.WorkoutStatus;
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

    private String name;

    private String description;

    private LocalDateTime scheduledDate;

    private WorkoutStatus status;

    private String comments;

    private List<WorkoutExerciseRequest> exercises;
}
