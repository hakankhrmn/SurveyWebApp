package com.surveyapp.controller;

import com.surveyapp.model.dto.UserDto;
import com.surveyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER')")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok(userService.save(userDto));
    }
}
