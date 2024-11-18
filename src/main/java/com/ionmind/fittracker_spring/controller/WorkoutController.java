package com.ionmind.fittracker_spring.controller;

import com.ionmind.fittracker_spring.dto.request.CreateWorkoutRequest;
import com.ionmind.fittracker_spring.dto.request.UpdateWorkoutRequest;
import com.ionmind.fittracker_spring.dto.response.WorkoutResponse;
import com.ionmind.fittracker_spring.model.enums.WorkoutStatus;
import com.ionmind.fittracker_spring.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {
    
    private final WorkoutService workoutService;
    
    @PostMapping
    public ResponseEntity<WorkoutResponse> createWorkout(@Valid @RequestBody CreateWorkoutRequest request) {
        WorkoutResponse response = workoutService.createWorkout(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<WorkoutResponse> updateWorkout(
            @PathVariable Long id,
            @Valid @RequestBody UpdateWorkoutRequest request) {
        WorkoutResponse response = workoutService.updateWorkout(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponse> getWorkoutById(@PathVariable Long id) {
        WorkoutResponse response = workoutService.getWorkoutById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutResponse>> getWorkoutsByUserId(@PathVariable Long userId) {
        List<WorkoutResponse> responses = workoutService.getWorkoutsByUserId(userId);
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<WorkoutResponse>> getActiveWorkouts(@PathVariable Long userId) {
        List<WorkoutResponse> responses = workoutService.getActiveWorkouts(userId);
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<WorkoutResponse>> getWorkoutsByStatus(
            @PathVariable Long userId,
            @PathVariable WorkoutStatus status) {
        List<WorkoutResponse> responses = workoutService.getWorkoutsByStatus(userId, status);
        return ResponseEntity.ok(responses);
    }
}
