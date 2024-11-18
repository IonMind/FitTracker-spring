package com.ionmind.fittracker_spring.service;

import com.ionmind.fittracker_spring.dto.response.ExerciseResponse;
import com.ionmind.fittracker_spring.exception.ResourceNotFoundException;
import com.ionmind.fittracker_spring.mapper.ExerciseMapper;
import com.ionmind.fittracker_spring.model.entity.Exercise;
import com.ionmind.fittracker_spring.model.enums.ExerciseCategory;
import com.ionmind.fittracker_spring.model.enums.MuscleGroup;
import com.ionmind.fittracker_spring.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExerciseService {
    
    private final ExerciseRepository exerciseRepository;
    
    public List<ExerciseResponse> getAllExercises() {
        log.info("Fetching all exercises");
        return exerciseRepository.findAll().stream()
                .map(ExerciseMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    public ExerciseResponse getExerciseById(Long id) {
        log.info("Fetching exercise with id: {}", id);
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + id));
        return ExerciseMapper.toResponse(exercise);
    }
    
    public List<ExerciseResponse> getExercisesByCategory(ExerciseCategory category) {
        log.info("Fetching exercises by category: {}", category);
        return exerciseRepository.findByCategory(category).stream()
                .map(ExerciseMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    public List<ExerciseResponse> getExercisesByMuscleGroup(MuscleGroup muscleGroup) {
        log.info("Fetching exercises by muscle group: {}", muscleGroup);
        return exerciseRepository.findByMuscleGroup(muscleGroup).stream()
                .map(ExerciseMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    public Exercise findExerciseById(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + id));
    }
}
