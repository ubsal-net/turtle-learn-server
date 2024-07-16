package com.dsu.turtlelearnserver.auth.service;

import com.dsu.turtlelearnserver.auth.dto.request.LoginRequestDto;
import com.dsu.turtlelearnserver.auth.dto.response.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenService tokenService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public LoginResponseDto processLogin(LoginRequestDto requestDto) {
        Authentication authentication = createAuthentication(requestDto);
        String accessToken = tokenService.generateAccessToken(authentication);
        return new LoginResponseDto(accessToken);
    }

    private Authentication createAuthentication(LoginRequestDto loginRequestDto) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
            loginRequestDto.username(), loginRequestDto.password());
        var authentication = authenticationManagerBuilder.getObject()
            .authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}