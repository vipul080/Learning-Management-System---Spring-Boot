# Learning Management System (LMS) - Spring Boot

A backend Learning Management System built using Java and Spring Boot.  
The application provides secure authentication, role-based access control, course management, and student enrollment features.

## Features

### Authentication & Authorization
- User registration and login
- JWT-based authentication
- Role-based access control:
    - ADMIN
    - TEACHER
    - STUDENT
- Password encryption using BCrypt

### User Management
- View all users (Admin)
- Update user roles (Admin)
- Secure user responses using DTOs

### Course Management
- Create courses
- Update courses
- Delete courses
- View all courses
- View course details
- Pagination and sorting support
- Automatic teacher assignment using authenticated user
- Teachers can only modify their own courses

### Enrollment System
- Students can enroll in courses
- Prevent duplicate enrollments
- Students can view their enrollments
- Students can cancel enrollment
- Teachers can view students enrolled in their courses

### API Features
- Request/Response DTO pattern
- Input validation
- Global exception handling
- Swagger/OpenAPI documentation

---

# Tech Stack

## Backend
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Maven

## Database
- PostgreSQL

## Security
- JWT Authentication
- BCrypt Password Encoder

## Documentation
- Swagger / OpenAPI

---

# Project Architecture

```
Controller
    |
    ↓
Service
    |
    ↓
Repository
    |
    ↓
Database
```

The application follows a layered architecture:

- Controller layer handles HTTP requests
- Service layer contains business logic
- Repository layer handles database operations
- DTOs handle API request and response objects

---

# Database Entities

## User

Stores application users.

Fields:
- id
- name
- email
- password
- role

Roles:
- ADMIN
- TEACHER
- STUDENT


## Course

Stores available courses.

Fields:
- id
- title
- description
- createdAt
- instructor


## Enrollment

Connects students and courses.

Fields:
- id
- student
- course
- enrolledAt

---

# API Documentation

Swagger UI is available at:

```
http://localhost:8081/swagger-ui/index.html
```

After logging in, JWT tokens can be used through the Swagger **Authorize** button.

---

# Running the Project

## 1. Clone the repository

```
git clone <repository-url>
```

## 2. Configure PostgreSQL

Create a database:

```
lms
```

Update database credentials in:

```
application.yml
```

Example:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/lms
    username: postgres
    password: your_password
```

---

## 3. Run the application

Using Maven:

```
mvn spring-boot:run
```

The application runs on:

```
http://localhost:8081
```

---

# Example Workflow

### Teacher

1. Register as teacher
2. Login and receive JWT token
3. Create a course
4. Manage own courses
5. View enrolled students


### Student

1. Register as student
2. Login
3. Browse courses
4. Enroll in a course
5. View enrolled courses


### Admin

1. Login
2. View users
3. Manage roles
4. Manage all courses and enrollments

---

# Future Improvements

- Frontend application
- Email notifications
- Course categories
- File uploads for course materials
- Unit testing
- Deployment using Docker

---

# Author

Vipul Shukla