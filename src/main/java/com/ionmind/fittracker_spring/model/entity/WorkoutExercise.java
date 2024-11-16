package com.ionmind.fittracker_spring.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "workout_exercises")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutExercise {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;
    
    @Column(nullable = false)
    private Integer sets;
    
    @Column(nullable = false)
    private Integer repetitions;
    
    @Column(precision = 5, scale = 2)
    private BigDecimal weight;
    
    @Column(name = "duration_minutes")
    private Integer durationMinutes;
    
    @Column(nullable = false, name = "order_index")
    private Integer orderIndex;
}
