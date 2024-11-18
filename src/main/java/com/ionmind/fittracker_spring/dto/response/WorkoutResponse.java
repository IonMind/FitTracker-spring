package com.ionmind.fittracker_spring.dto.response;

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
public class WorkoutResponse {
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private LocalDateTime scheduledDate;
    private WorkoutStatus status;
    private String comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<WorkoutExerciseResponse> exercises;
}
