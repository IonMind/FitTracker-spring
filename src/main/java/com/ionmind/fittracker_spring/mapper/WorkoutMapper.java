package com.ionmind.fittracker_spring.mapper;

import com.ionmind.fittracker_spring.dto.response.WorkoutExerciseResponse;
import com.ionmind.fittracker_spring.dto.response.WorkoutResponse;
import com.ionmind.fittracker_spring.model.entity.Workout;
import com.ionmind.fittracker_spring.model.entity.WorkoutExercise;

import java.util.stream.Collectors;

public class WorkoutMapper {
    
    public static WorkoutResponse toResponse(Workout workout) {
        return WorkoutResponse.builder()
                .id(workout.getId())
                .userId(workout.getUser().getId())
                .name(workout.getName())
                .description(workout.getDescription())
                .scheduledDate(workout.getScheduledDate())
                .status(workout.getStatus())
                .comments(workout.getComments())
                .createdAt(workout.getCreatedAt())
                .updatedAt(workout.getUpdatedAt())
                .exercises(workout.getWorkoutExercises().stream()
                        .map(WorkoutMapper::toWorkoutExerciseResponse)
                        .collect(Collectors.toList()))
                .build();
    }
    
    public static WorkoutExerciseResponse toWorkoutExerciseResponse(WorkoutExercise workoutExercise) {
        return WorkoutExerciseResponse.builder()
                .id(workoutExercise.getId())
                .exercise(ExerciseMapper.toResponse(workoutExercise.getExercise()))
                .sets(workoutExercise.getSets())
                .repetitions(workoutExercise.getRepetitions())
                .weight(workoutExercise.getWeight())
                .durationMinutes(workoutExercise.getDurationMinutes())
                .orderIndex(workoutExercise.getOrderIndex())
                .build();
    }
}
