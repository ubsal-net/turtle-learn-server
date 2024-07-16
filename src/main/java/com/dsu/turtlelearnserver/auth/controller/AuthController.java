package com.dsu.turtlelearnserver.auth.controller;

import com.dsu.turtlelearnserver.auth.dto.request.LoginRequestDto;
import com.dsu.turtlelearnserver.auth.dto.response.LoginResponseDto;
import com.dsu.turtlelearnserver.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auths")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> processLogin(
        @RequestBody LoginRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.processLogin(requestDto));
    }
}
