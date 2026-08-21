package com.lms.controller;

import com.lms.entity.Enrollment;
import com.lms.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(
                enrollmentService.getAllEnrollments()
        );
    }

    @PostMapping("/{courseId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Enrollment> enroll(
            @PathVariable Long courseId,
            Authentication authentication) {

        Enrollment enrollment = enrollmentService.enrollStudent(
                authentication.getName(),
                courseId
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(enrollment);
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<Enrollment>> getMyEnrollments(
            Authentication authentication) {

        return ResponseEntity.ok(
                enrollmentService.getMyEnrollments(authentication.getName())
        );
    }

    @DeleteMapping("/{enrollmentId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Void> cancelEnrollment(
            @PathVariable Long enrollmentId,
            Authentication authentication) {

        enrollmentService.cancelEnrollment(
                enrollmentId,
                authentication.getName()
        );

        return ResponseEntity.noContent().build();
    }
}