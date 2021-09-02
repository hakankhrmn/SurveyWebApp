package com.surveyapp.controller;


import com.surveyapp.config.JwtTokenManager;
import com.surveyapp.model.AuthenticationRequest;
import com.surveyapp.model.AuthenticationResponse;
import com.surveyapp.model.dto.UserDto;
import com.surveyapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class AuthController {

    private JwtTokenManager jwtTokenManager;
    private AuthenticationManager authenticationManager;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(JwtTokenManager jwtTokenManager, AuthenticationManager authenticationManager, UserService userService, ModelMapper modelMapper) {
        this.jwtTokenManager = jwtTokenManager;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));


        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        }
        catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        UserDetails userdetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtTokenManager.generateToken(userdetails);

        UserDto userDto = modelMapper.map(userService.getUserByUserMail(authenticationRequest.getUsername()), UserDto.class);
        return ResponseEntity.ok(new AuthenticationResponse(token,userDto));

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok(userService.save(userDto));
    }
}