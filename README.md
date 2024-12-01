# FitTracker Spring Boot API

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/IonMind/FitTracker-spring)
[![Java Version](https://img.shields.io/badge/Java-17-blue)](https://adoptopenjdk.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/license-MIT-lightgrey)](LICENSE)

---

## Project Overview
FitTracker is a RESTful API for tracking fitness activities, exercises, and workouts. It provides secure user authentication, exercise management, and workout tracking, making it easy to build fitness applications or integrate with mobile/web clients.

---

## Technologies & Stack
- **Java 17**
- **Spring Boot 3.3.5**
- **Spring Data JPA**
- **Spring Security (JWT)**
- **H2 In-Memory Database**
- **Lombok**
- **Maven**

---

## Features
- User registration and login with JWT authentication
- Secure endpoints for managing exercises and workouts
- Exercise categorization by muscle group and category
- Workout tracking with status management
- Automated data seeding for development
- RESTful API design

---

## API Endpoints

### Auth (`/api/auth`)
- `POST /register` — Register a new user
- `POST /login` — Login and receive JWT token
- `GET /me` — Get current user profile

### Exercises (`/api/exercises`)
- `GET /` — List all exercises
- `GET /{id}` — Get exercise by ID
- `GET /category/{category}` — List by category
- `GET /muscle-group/{muscleGroup}` — List by muscle group

### Workouts (`/api/workouts`)
- `POST /` — Create a workout
- `PUT /{id}` — Update a workout
- `DELETE /{id}` — Delete a workout
- `GET /{id}` — Get workout by ID
- `GET /` — List all workouts
- `GET /active` — List active workouts
- `GET /status/{status}` — List by status

---

## Workflow
1. **Register** a new user via `/api/auth/register`.
2. **Login** to receive a JWT token via `/api/auth/login`.
3. Use the **JWT token** as a Bearer token in the `Authorization` header for all protected endpoints.
4. **Manage exercises** and **track workouts** using the provided endpoints.

---

## Prerequisites, Installation & Setup

### Prerequisites
- Java 17+
- Maven 3.6+

### Installation & Running
```bash
# Clone the repository
git clone https://github.com/IonMind/FitTracker-spring.git
cd FitTracker-spring

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```
- The API will be available at `http://localhost:8080/`
- H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:fittrackerdb`)

---

## Database Models
- **User**: Stores user credentials and profile
- **Exercise**: Represents exercises, categorized by type and muscle group
- **Workout**: Tracks workout sessions and their status
- **WorkoutExercise**: Links workouts and exercises (many-to-many)
- **Enums**: `ExerciseCategory`, `MuscleGroup`, `WorkoutStatus`

---

## Postman Collection
- The file `FitTracker_Postman_Collection.json` is included for API testing.
- **Important:** After login, update the Postman environment variable for the Bearer token to authenticate protected requests.

---

## Project Structure
```
fittracker-spring/
├── src/
│   ├── main/
│   │   ├── java/com/ionmind/fittracker_spring/
│   │   │   ├── controller/      # REST controllers
│   │   │   ├── service/         # Business logic
│   │   │   ├── repository/      # Data access
│   │   │   ├── model/
│   │   │   │   ├── entity/      # JPA entities
│   │   │   │   └── enums/       # Enum types
│   │   │   ├── dto/             # Request/response DTOs
│   │   │   ├── config/          # Configuration classes
│   │   │   ├── security/        # Security and JWT
│   │   │   ├── exception/       # Custom exceptions & handlers
│   │   │   └── mapper/          # Entity-DTO mappers
│   │   └── resources/
│   │       ├── application.yml  # Main config
│   │       └── ...
│   └── test/
│       └── ...
├── pom.xml
├── FitTracker_Postman_Collection.json
└── README.md
```

---

For questions or contributions, please open an issue or pull request on GitHub.
