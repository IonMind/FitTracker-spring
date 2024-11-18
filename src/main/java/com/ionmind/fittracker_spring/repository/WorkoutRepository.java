package com.ionmind.fittracker_spring.repository;

import com.ionmind.fittracker_spring.model.entity.Workout;
import com.ionmind.fittracker_spring.model.enums.WorkoutStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    
    // Find all workouts for a user
    List<Workout> findByUserId(Long userId);
    
    // Find workouts for a user with a specific status
    List<Workout> findByUserIdAndStatus(Long userId, WorkoutStatus status);
    
    // Find workouts for a user with status in a list, sorted by scheduled date
    List<Workout> findByUserIdAndStatusInOrderByScheduledDateAsc(Long userId, List<WorkoutStatus> statuses);
    
    // Find workouts for a user within a date range, sorted by scheduled date
    List<Workout> findByUserIdAndScheduledDateBetweenOrderByScheduledDateAsc(
        Long userId,
        LocalDateTime startDate,
        LocalDateTime endDate
    );
    
    // Find all workouts for a user, sorted by scheduled date
    List<Workout> findByUserIdOrderByScheduledDateAsc(Long userId);
}
