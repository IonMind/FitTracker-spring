package com.ionmind.fittracker_spring.controller;

import com.ionmind.fittracker_spring.dto.request.CreateWorkoutRequest;
import com.ionmind.fittracker_spring.dto.request.UpdateWorkoutRequest;
import com.ionmind.fittracker_spring.dto.response.WorkoutResponse;
import com.ionmind.fittracker_spring.model.enums.WorkoutStatus;
import com.ionmind.fittracker_spring.security.CustomUserDetails;
import com.ionmind.fittracker_spring.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {
    
    private final WorkoutService workoutService;
    
    @PostMapping
    public ResponseEntity<WorkoutResponse> createWorkout(
            @Valid @RequestBody CreateWorkoutRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        request.setUserId(userDetails.getId());
        WorkoutResponse response = workoutService.createWorkout(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<WorkoutResponse> updateWorkout(
            @PathVariable Long id,
            @Valid @RequestBody UpdateWorkoutRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        WorkoutResponse response = workoutService.updateWorkout(id, request, userDetails.getId());
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        workoutService.deleteWorkout(id, userDetails.getId());
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponse> getWorkoutById(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        WorkoutResponse response = workoutService.getWorkoutById(id, userDetails.getId());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<WorkoutResponse>> getMyWorkouts(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<WorkoutResponse> responses = workoutService.getWorkoutsByUserId(userDetails.getId());
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<WorkoutResponse>> getMyActiveWorkouts(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<WorkoutResponse> responses = workoutService.getActiveWorkouts(userDetails.getId());
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<WorkoutResponse>> getMyWorkoutsByStatus(
            @PathVariable WorkoutStatus status,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<WorkoutResponse> responses = workoutService.getWorkoutsByStatus(userDetails.getId(), status);
        return ResponseEntity.ok(responses);
    }
}
