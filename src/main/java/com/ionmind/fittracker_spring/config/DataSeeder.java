package com.ionmind.fittracker_spring.config;

import com.ionmind.fittracker_spring.model.entity.Exercise;
import com.ionmind.fittracker_spring.model.entity.User;
import com.ionmind.fittracker_spring.model.enums.ExerciseCategory;
import com.ionmind.fittracker_spring.model.enums.MuscleGroup;
import com.ionmind.fittracker_spring.repository.ExerciseRepository;
import com.ionmind.fittracker_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Data seeder for demo purposes.
 * Seeds initial data (users and exercises) before the application starts accepting requests
 * to simulate a running database with sample data.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {
    
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("Starting data seeder...");
        
        seedUsers();
        seedExercises();
        
        log.info("Data seeding completed!");
    }
    
    private void seedUsers() {
        if (userRepository.count() == 0) {
            log.info("Seeding users...");
            
            List<User> users = Arrays.asList(
                User.builder()
                    .username("johndoe")
                    .email("john.doe@example.com")
                    .fullName("John Doe")
                    .build(),
                User.builder()
                    .username("janedoe")
                    .email("jane.doe@example.com")
                    .fullName("Jane Doe")
                    .build()
            );
            
            userRepository.saveAll(users);
            log.info("Seeded {} users", users.size());
        }
    }
    
    private void seedExercises() {
        if (exerciseRepository.count() == 0) {
            log.info("Seeding exercises...");
            
            List<Exercise> exercises = Arrays.asList(
                // Strength - Chest
                Exercise.builder()
                    .name("Bench Press")
                    .description("Lie on a flat bench and press a barbell or dumbbells upward from chest level")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.CHEST)
                    .build(),
                Exercise.builder()
                    .name("Push-ups")
                    .description("Classic bodyweight exercise targeting chest, shoulders, and triceps")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.CHEST)
                    .build(),
                Exercise.builder()
                    .name("Dumbbell Flyes")
                    .description("Lying on a bench, arc dumbbells out to the sides and bring them back together")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.CHEST)
                    .build(),
                
                // Strength - Back
                Exercise.builder()
                    .name("Pull-ups")
                    .description("Hang from a bar and pull your body up until chin is above the bar")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.BACK)
                    .build(),
                Exercise.builder()
                    .name("Deadlift")
                    .description("Lift a loaded barbell from the ground to hip level")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.BACK)
                    .build(),
                Exercise.builder()
                    .name("Bent Over Row")
                    .description("Bend at hips and pull barbell or dumbbells to your torso")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.BACK)
                    .build(),
                
                // Strength - Legs
                Exercise.builder()
                    .name("Squats")
                    .description("Lower your body by bending knees and hips, keeping back straight")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.LEGS)
                    .build(),
                Exercise.builder()
                    .name("Lunges")
                    .description("Step forward and lower your body until both knees are bent at 90 degrees")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.LEGS)
                    .build(),
                Exercise.builder()
                    .name("Leg Press")
                    .description("Push weight away from body using legs while seated")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.LEGS)
                    .build(),
                
                // Strength - Arms
                Exercise.builder()
                    .name("Bicep Curls")
                    .description("Curl dumbbells or barbell up towards shoulders, targeting biceps")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.ARMS)
                    .build(),
                Exercise.builder()
                    .name("Tricep Dips")
                    .description("Lower and raise body using arm strength on parallel bars or bench")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.ARMS)
                    .build(),
                
                // Strength - Shoulders
                Exercise.builder()
                    .name("Overhead Press")
                    .description("Press barbell or dumbbells overhead from shoulder level")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.SHOULDERS)
                    .build(),
                Exercise.builder()
                    .name("Lateral Raises")
                    .description("Raise dumbbells to the sides until arms are parallel to floor")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.SHOULDERS)
                    .build(),
                
                // Strength - Core
                Exercise.builder()
                    .name("Plank")
                    .description("Hold a push-up position with body straight, resting on forearms")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.CORE)
                    .build(),
                Exercise.builder()
                    .name("Crunches")
                    .description("Lie on back and lift shoulders off ground, targeting abdominals")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.CORE)
                    .build(),
                Exercise.builder()
                    .name("Russian Twists")
                    .description("Sit with knees bent, lean back slightly and rotate torso side to side")
                    .category(ExerciseCategory.STRENGTH)
                    .muscleGroup(MuscleGroup.CORE)
                    .build(),
                
                // Cardio
                Exercise.builder()
                    .name("Running")
                    .description("Cardiovascular exercise performed at various speeds and distances")
                    .category(ExerciseCategory.CARDIO)
                    .muscleGroup(MuscleGroup.FULL_BODY)
                    .build(),
                Exercise.builder()
                    .name("Cycling")
                    .description("Ride a stationary or outdoor bike for cardiovascular fitness")
                    .category(ExerciseCategory.CARDIO)
                    .muscleGroup(MuscleGroup.LEGS)
                    .build(),
                Exercise.builder()
                    .name("Jump Rope")
                    .description("Skip rope continuously for cardiovascular conditioning")
                    .category(ExerciseCategory.CARDIO)
                    .muscleGroup(MuscleGroup.FULL_BODY)
                    .build(),
                Exercise.builder()
                    .name("Burpees")
                    .description("Full body exercise combining squat, plank, and jump")
                    .category(ExerciseCategory.CARDIO)
                    .muscleGroup(MuscleGroup.FULL_BODY)
                    .build(),
                
                // Flexibility
                Exercise.builder()
                    .name("Yoga")
                    .description("Practice various poses to improve flexibility, balance, and strength")
                    .category(ExerciseCategory.FLEXIBILITY)
                    .muscleGroup(MuscleGroup.FULL_BODY)
                    .build(),
                Exercise.builder()
                    .name("Hamstring Stretch")
                    .description("Stretch hamstrings by reaching towards toes while seated or standing")
                    .category(ExerciseCategory.FLEXIBILITY)
                    .muscleGroup(MuscleGroup.LEGS)
                    .build(),
                Exercise.builder()
                    .name("Shoulder Stretch")
                    .description("Stretch shoulders by pulling arm across body or overhead")
                    .category(ExerciseCategory.FLEXIBILITY)
                    .muscleGroup(MuscleGroup.SHOULDERS)
                    .build(),
                
                // Balance
                Exercise.builder()
                    .name("Single Leg Stand")
                    .description("Stand on one leg to improve balance and stability")
                    .category(ExerciseCategory.BALANCE)
                    .muscleGroup(MuscleGroup.LEGS)
                    .build(),
                Exercise.builder()
                    .name("Bosu Ball Exercises")
                    .description("Perform exercises on a Bosu ball for balance training")
                    .category(ExerciseCategory.BALANCE)
                    .muscleGroup(MuscleGroup.FULL_BODY)
                    .build()
            );
            
            exerciseRepository.saveAll(exercises);
            log.info("Seeded {} exercises", exercises.size());
        }
    }
}
