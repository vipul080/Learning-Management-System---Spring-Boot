package com.lms.dto;

import com.lms.entity.Course;

import java.time.LocalDateTime;

public class CourseResponseDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;

    public CourseResponseDTO(Course course) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.description = course.getDescription();
        this.createdAt = course.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}