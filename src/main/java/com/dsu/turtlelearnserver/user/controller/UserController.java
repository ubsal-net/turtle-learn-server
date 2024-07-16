package com.dsu.turtlelearnserver.user.controller;

import com.dsu.turtlelearnserver.user.dto.request.RegistrationForm;
import com.dsu.turtlelearnserver.user.dto.response.RegistrationResponse;
import com.dsu.turtlelearnserver.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<RegistrationResponse> signUp(
        @Valid @RequestBody RegistrationForm request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(request));
    }
}
