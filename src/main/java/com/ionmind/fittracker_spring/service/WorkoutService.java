package com.ionmind.fittracker_spring.service;

import com.ionmind.fittracker_spring.dto.request.CreateWorkoutRequest;
import com.ionmind.fittracker_spring.dto.request.UpdateWorkoutRequest;
import com.ionmind.fittracker_spring.dto.request.WorkoutExerciseRequest;
import com.ionmind.fittracker_spring.dto.response.WorkoutResponse;
import com.ionmind.fittracker_spring.exception.ResourceNotFoundException;
import com.ionmind.fittracker_spring.mapper.WorkoutMapper;
import com.ionmind.fittracker_spring.model.entity.Exercise;
import com.ionmind.fittracker_spring.model.entity.User;
import com.ionmind.fittracker_spring.model.entity.Workout;
import com.ionmind.fittracker_spring.model.entity.WorkoutExercise;
import com.ionmind.fittracker_spring.model.enums.WorkoutStatus;
import com.ionmind.fittracker_spring.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkoutService {
    
    private final WorkoutRepository workoutRepository;
    private final UserService userService;
    private final ExerciseService exerciseService;
    
    @Transactional
    public WorkoutResponse createWorkout(CreateWorkoutRequest request) {
        log.info("Creating workout: {} for user: {}", request.getName(), request.getUserId());
        
        User user = userService.findUserById(request.getUserId());
        
        Workout workout = Workout.builder()
                .user(user)
                .name(request.getName())
                .description(request.getDescription())
                .scheduledDate(request.getScheduledDate())
                .status(request.getStatus() != null ? request.getStatus() : WorkoutStatus.PENDING)
                .comments(request.getComments())
                .workoutExercises(new ArrayList<>())
                .build();
        
        // Add workout exercises
        for (WorkoutExerciseRequest exerciseRequest : request.getExercises()) {
            Exercise exercise = exerciseService.findExerciseById(exerciseRequest.getExerciseId());
            
            WorkoutExercise workoutExercise = WorkoutExercise.builder()
                    .workout(workout)
                    .exercise(exercise)
                    .sets(exerciseRequest.getSets())
                    .repetitions(exerciseRequest.getRepetitions())
                    .weight(exerciseRequest.getWeight())
                    .durationMinutes(exerciseRequest.getDurationMinutes())
                    .orderIndex(exerciseRequest.getOrderIndex())
                    .build();
            
            workout.addWorkoutExercise(workoutExercise);
        }
        
        Workout savedWorkout = workoutRepository.save(workout);
        log.info("Workout created successfully with id: {}", savedWorkout.getId());
        
        return WorkoutMapper.toResponse(savedWorkout);
    }
    
    @Transactional
    public WorkoutResponse updateWorkout(Long workoutId, UpdateWorkoutRequest request, Long userId) {
        log.info("Updating workout with id: {}", workoutId);
        
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found with id: " + workoutId));
        
        // Check if the workout belongs to the authenticated user
        if (!workout.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Workout not found with id: " + workoutId);
        }
        
        // Update basic fields
        if (request.getName() != null) {
            workout.setName(request.getName());
        }
        if (request.getDescription() != null) {
            workout.setDescription(request.getDescription());
        }
        if (request.getScheduledDate() != null) {
            workout.setScheduledDate(request.getScheduledDate());
        }
        if (request.getStatus() != null) {
            workout.setStatus(request.getStatus());
        }
        if (request.getComments() != null) {
            workout.setComments(request.getComments());
        }
        
        // Update exercises if provided
        if (request.getExercises() != null && !request.getExercises().isEmpty()) {
            // Clear existing exercises
            workout.getWorkoutExercises().clear();
            
            // Add new exercises
            for (WorkoutExerciseRequest exerciseRequest : request.getExercises()) {
                Exercise exercise = exerciseService.findExerciseById(exerciseRequest.getExerciseId());
                
                WorkoutExercise workoutExercise = WorkoutExercise.builder()
                        .workout(workout)
                        .exercise(exercise)
                        .sets(exerciseRequest.getSets())
                        .repetitions(exerciseRequest.getRepetitions())
                        .weight(exerciseRequest.getWeight())
                        .durationMinutes(exerciseRequest.getDurationMinutes())
                        .orderIndex(exerciseRequest.getOrderIndex())
                        .build();
                
                workout.addWorkoutExercise(workoutExercise);
            }
        }
        
        Workout updatedWorkout = workoutRepository.save(workout);
        log.info("Workout updated successfully with id: {}", updatedWorkout.getId());
        
        return WorkoutMapper.toResponse(updatedWorkout);
    }
    
    @Transactional
    public void deleteWorkout(Long workoutId, Long userId) {
        log.info("Deleting workout with id: {}", workoutId);
        
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found with id: " + workoutId));
        
        // Check if the workout belongs to the authenticated user
        if (!workout.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Workout not found with id: " + workoutId);
        }
        
        workoutRepository.deleteById(workoutId);
        log.info("Workout deleted successfully with id: {}", workoutId);
    }
    
    @Transactional(readOnly = true)
    public WorkoutResponse getWorkoutById(Long workoutId, Long userId) {
        log.info("Fetching workout with id: {}", workoutId);
        
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found with id: " + workoutId));
        
        // Check if the workout belongs to the authenticated user
        if (!workout.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Workout not found with id: " + workoutId);
        }
        
        return WorkoutMapper.toResponse(workout);
    }
    
    @Transactional(readOnly = true)
    public List<WorkoutResponse> getWorkoutsByUserId(Long userId) {
        log.info("Fetching all workouts for user: {}", userId);
        
        // Verify user exists
        userService.findUserById(userId);
        
        List<Workout> workouts = workoutRepository.findByUserIdOrderByScheduledDateAsc(userId);
        return workouts.stream()
                .map(WorkoutMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<WorkoutResponse> getActiveWorkouts(Long userId) {
        log.info("Fetching active/pending workouts for user: {}", userId);
        
        // Verify user exists
        userService.findUserById(userId);
        
        List<WorkoutStatus> activeStatuses = Arrays.asList(WorkoutStatus.PENDING);
        List<Workout> workouts = workoutRepository.findByUserIdAndStatusInOrderByScheduledDateAsc(userId, activeStatuses);
        
        return workouts.stream()
                .map(WorkoutMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<WorkoutResponse> getWorkoutsByStatus(Long userId, WorkoutStatus status) {
        log.info("Fetching workouts with status {} for user: {}", status, userId);
        
        // Verify user exists
        userService.findUserById(userId);
        
        List<Workout> workouts = workoutRepository.findByUserIdAndStatus(userId, status);
        return workouts.stream()
                .map(WorkoutMapper::toResponse)
                .collect(Collectors.toList());
    }
}
