package com.ionmind.fittracker_spring.controller;

import com.ionmind.fittracker_spring.dto.response.ExerciseResponse;
import com.ionmind.fittracker_spring.model.enums.ExerciseCategory;
import com.ionmind.fittracker_spring.model.enums.MuscleGroup;
import com.ionmind.fittracker_spring.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {
    
    private final ExerciseService exerciseService;
    
    @GetMapping
    public ResponseEntity<List<ExerciseResponse>> getAllExercises() {
        List<ExerciseResponse> responses = exerciseService.getAllExercises();
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponse> getExerciseById(@PathVariable Long id) {
        ExerciseResponse response = exerciseService.getExerciseById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ExerciseResponse>> getExercisesByCategory(@PathVariable ExerciseCategory category) {
        List<ExerciseResponse> responses = exerciseService.getExercisesByCategory(category);
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/muscle-group/{muscleGroup}")
    public ResponseEntity<List<ExerciseResponse>> getExercisesByMuscleGroup(@PathVariable MuscleGroup muscleGroup) {
        List<ExerciseResponse> responses = exerciseService.getExercisesByMuscleGroup(muscleGroup);
        return ResponseEntity.ok(responses);
    }
}
