package com.ionmind.fittracker_spring.repository;

import com.ionmind.fittracker_spring.model.entity.Exercise;
import com.ionmind.fittracker_spring.model.enums.ExerciseCategory;
import com.ionmind.fittracker_spring.model.enums.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    
    Optional<Exercise> findByName(String name); //for future enhancements
    
    List<Exercise> findByCategory(ExerciseCategory category);
    
    List<Exercise> findByMuscleGroup(MuscleGroup muscleGroup);
    
    List<Exercise> findByCategoryAndMuscleGroup(ExerciseCategory category, MuscleGroup muscleGroup);
    
    boolean existsByName(String name); // for future enhancements
}
