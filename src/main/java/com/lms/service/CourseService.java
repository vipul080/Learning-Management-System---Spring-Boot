package com.lms.service;

import com.lms.dto.CourseRequestDTO;
import com.lms.dto.CourseResponseDTO;
import com.lms.entity.Course;
import com.lms.exception.ResourceNotFoundException;
import com.lms.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(CourseRequestDTO request) {

        Course course = new Course(
                request.getTitle(),
                request.getDescription()
        );

        return courseRepository.save(course);
    }

    public List<CourseResponseDTO> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(CourseResponseDTO::new)
                .toList();
    }

    public CourseResponseDTO getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found")
                );

        return new CourseResponseDTO(course);
    }

    public void deleteCourse(Long id) {

        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found");
        }

        courseRepository.deleteById(id);
    }

    public Course updateCourse(Long id, CourseRequestDTO request) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found")
                );

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());

        return courseRepository.save(course);
    }
}