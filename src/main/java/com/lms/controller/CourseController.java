package com.lms.controller;

import com.lms.dto.CourseRequestDTO;
import com.lms.dto.CourseResponseDTO;
import com.lms.dto.StudentResponseDTO;
import com.lms.entity.Course;
import com.lms.service.CourseService;
import com.lms.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public CourseController(CourseService courseService, EnrollmentService enrollmentService) {
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<CourseResponseDTO> createCourse(
            @Valid @RequestBody CourseRequestDTO request,
            Authentication authentication
    ) {
        Course savedCourse = courseService.createCourse(
                request,
                authentication.getName()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CourseResponseDTO(savedCourse));
    }

    @GetMapping
    public ResponseEntity<Page<CourseResponseDTO>> getAllCourses(
            Pageable pageable
    ) {
        return ResponseEntity.ok(courseService.getAllCourses(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id, Authentication authentication) {
        courseService.deleteCourse(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDTO request, Authentication authentication) {
        Course updatedCourse = courseService.updateCourse(
                id,
                request,
                authentication.getName()
        );

        return ResponseEntity.ok(
                new CourseResponseDTO(updatedCourse)
        );
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<List<CourseResponseDTO>> getMyCourses(
            Authentication authentication
    ) {

        return ResponseEntity.ok(
                courseService.getMyCourses(
                        authentication.getName()
                )
        );
    }

    @GetMapping("/{courseId}/students")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<List<StudentResponseDTO>> getCourseStudents(
            @PathVariable Long courseId,
            Authentication authentication
    ) {

        return ResponseEntity.ok(
                enrollmentService.getStudentsForCourse(
                        courseId,
                        authentication.getName()
                )
        );
    }
}