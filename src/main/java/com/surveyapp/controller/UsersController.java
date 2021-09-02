package com.surveyapp.controller;

import com.surveyapp.model.User;
import com.surveyapp.model.dto.UserDto;
import com.surveyapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UsersController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UsersController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create-user")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER')")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping("/")
    public ResponseEntity<UserDto> getUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username="";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userService.getUserByUserMail(username);
        UserDto userDto = null;
        if(user != null){
            userDto= modelMapper.map(user, UserDto.class);
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
