package com.lms.dto;

import com.lms.entity.User;

public class StudentResponseDTO {

    private Long id;
    private String name;
    private String email;

    public StudentResponseDTO(User student) {
        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}