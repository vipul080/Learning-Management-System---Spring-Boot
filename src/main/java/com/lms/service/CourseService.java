package com.lms.service;

import com.lms.dto.CourseRequestDTO;
import com.lms.dto.CourseResponseDTO;
import com.lms.entity.Course;
import com.lms.entity.User;
import com.lms.exception.ForbiddenException;
import com.lms.repository.UserRepository;
import com.lms.exception.ResourceNotFoundException;
import com.lms.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Course createCourse(CourseRequestDTO request, String email) {

        User instructor = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        Course course = new Course(
                request.getTitle(),
                request.getDescription()
        );

        course.setInstructor(instructor);

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

    public void deleteCourse(Long id, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found")
                );

        if (user.getRole().name().equals("TEACHER")
                && !course.getInstructor().getId().equals(user.getId())) {

            throw new ForbiddenException(
                    "You cannot delete another teacher's course"
            );
        }

        courseRepository.delete(course);
    }

    public Course updateCourse(Long id, CourseRequestDTO request, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found")
                );
        if (user.getRole().name().equals("TEACHER")
                && !course.getInstructor().getId().equals(user.getId())) {

            throw new ForbiddenException(
                    "You cannot modify another teacher's course"
            );
        }

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());

        return courseRepository.save(course);
    }

    public List<CourseResponseDTO> getMyCourses(String email) {

        User instructor = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        return courseRepository.findByInstructor(instructor)
                .stream()
                .map(CourseResponseDTO::new)
                .toList();
    }
}