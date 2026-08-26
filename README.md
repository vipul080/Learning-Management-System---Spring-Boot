# Learning Management System (LMS) - Spring Boot

A backend Learning Management System built using Java and Spring Boot.

The application provides secure authentication, role-based access control, course management, student enrollment, API documentation, and containerized deployment.

## Features

### Authentication & Authorization

* User registration and login
* JWT-based authentication
* Role-based access control:

  * ADMIN
  * TEACHER
  * STUDENT
* Password encryption using BCrypt
* JWT authentication support in Swagger

### User Management

* View all users (Admin)
* Update user roles (Admin)
* Secure user responses using DTOs

### Course Management

* Create courses
* Update courses
* Delete courses
* View course details
* Pagination and sorting support
* Automatic teacher assignment using authenticated user
* Teachers can only modify their own courses

### Enrollment System

* Students can enroll in courses
* Prevent duplicate enrollments
* Students can view their enrollments
* Students can cancel enrollment
* Teachers can view students enrolled in their courses

### API Features

* Request/Response DTO pattern
* Input validation
* Global exception handling
* Custom exception handling
* Proper HTTP status codes
* Swagger/OpenAPI documentation

### Docker

* Dockerized Spring Boot application
* PostgreSQL running in a Docker container
* Docker Compose for multi-container setup
* Persistent PostgreSQL volume
* Environment-based configuration for database credentials and JWT secret

---

# Tech Stack

## Backend

* Java 21
* Spring Boot 4
* Spring Security
* Spring Data JPA
* Hibernate
* Maven

## Database

* PostgreSQL 18

## Security

* JWT Authentication
* BCrypt Password Encoder

## Documentation

* Swagger / OpenAPI

## DevOps

* Docker
* Docker Compose

---

# Project Architecture

```text
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

* **Controller layer** handles HTTP requests
* **Service layer** contains business logic
* **Repository layer** handles database operations
* **DTOs** handle API request and response objects
* **Exception layer** provides centralized error handling

---

# Database Entities

## User

Stores application users.

Fields:

* id
* name
* email
* password
* role

Roles:

* ADMIN
* TEACHER
* STUDENT

## Course

Stores available courses.

Fields:

* id
* title
* description
* createdAt
* instructor

## Enrollment

Connects students and courses.

Fields:

* id
* student
* course
* enrolledAt

---

# API Documentation

Swagger UI is available at:

```text
http://localhost:8081/swagger-ui/index.html
```

After logging in, JWT tokens can be used through the Swagger **Authorize** button.

---

# Running the Project

## Option 1: Run Locally

### 1. Clone the repository

```bash
git clone <repository-url>
cd Learning-Management-System---Spring-Boot
```

### 2. Configure PostgreSQL

Create a PostgreSQL database:

```text
lms
```

Configure the required environment variables:

```text
DB_URL=jdbc:postgresql://localhost:5432/lms
DB_PASSWORD=your_database_password
JWT_SECRET=your_jwt_secret
```

Do not commit your `.env` file or expose your JWT secret.

### 3. Build the project

On Windows:

```powershell
.\mvnw.cmd clean package
```

### 4. Run the application

```powershell
.\mvnw.cmd spring-boot:run
```

The application runs on:

```text
http://localhost:8081
```

---

# Running with Docker

Make sure Docker Desktop is installed and running.

### 1. Configure environment variables

Create a `.env` file in the project root:

```env
DB_PASSWORD=your_database_password
JWT_SECRET=your_jwt_secret
```

The `.env` file should not be committed to GitHub.

### 2. Build and start the application

```bash
docker compose up --build
```

This starts:

```text
Spring Boot application
        |
        ↓
PostgreSQL container
```

The application will be available at:

```text
http://localhost:8081
```

Swagger UI:

```text
http://localhost:8081/swagger-ui/index.html
```

### 3. Stop the application

```bash
docker compose down
```

PostgreSQL data is stored in a persistent Docker volume, so normal container shutdowns do not remove the database data.

---

# Example Workflow

### Teacher

1. Register as a teacher
2. Login and receive a JWT token
3. Create a course
4. Manage their own courses
5. View students enrolled in their courses

### Student

1. Register as a student
2. Login and receive a JWT token
3. Browse available courses
4. Enroll in a course
5. View enrolled courses
6. Cancel an enrollment

### Admin

1. Login and receive a JWT token
2. View registered users
3. Manage user roles
4. Manage courses
5. View all enrollments

---

# Future Improvements

* Frontend application
* Email notifications
* Course categories
* File uploads for course materials
* Unit and integration testing
* Cloud deployment

---

# Author

Vipul Shukla
