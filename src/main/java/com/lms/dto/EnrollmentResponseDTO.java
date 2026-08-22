package com.lms.dto;

import com.lms.entity.Enrollment;

import java.time.LocalDateTime;

public class EnrollmentResponseDTO {

    private Long id;
    private Long courseId;
    private String courseTitle;
    private String studentEmail;
    private LocalDateTime enrolledAt;

    public EnrollmentResponseDTO(Enrollment enrollment) {
        this.id = enrollment.getId();
        this.courseId = enrollment.getCourse().getId();
        this.courseTitle = enrollment.getCourse().getTitle();
        this.studentEmail = enrollment.getStudent().getEmail();
        this.enrolledAt = enrollment.getEnrolledAt();
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }
}