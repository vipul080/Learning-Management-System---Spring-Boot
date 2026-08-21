package com.lms.controller;

import com.lms.dto.RoleUpdateRequest;
import com.lms.dto.UserResponseDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.lms.entity.User;
import com.lms.service.UserService;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public String getCurrentUser(Authentication authentication) {
        return "User: " + authentication.getName()
                + " | Authorities: " + authentication.getAuthorities();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Welcome Admin!";
    }


    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> updateRole(
            @PathVariable Long id,
            @RequestBody RoleUpdateRequest request
    ) {

        User user = userService.updateRole(
                id,
                request.getRole()
        );

        return ResponseEntity.ok(
                new UserResponseDTO(user)
        );
    }
}