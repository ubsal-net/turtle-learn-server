package com.dsu.turtlelearnserver.auth.config;

import com.dsu.turtlelearnserver.auth.exception.JwtErrorCode;
import com.dsu.turtlelearnserver.global.exception.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**/
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final String EXCEPTION_KEY = "exception";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException {
        JwtErrorCode code = (JwtErrorCode) request.getAttribute(EXCEPTION_KEY);
        setResponse(response, code);
    }

    private void setResponse(HttpServletResponse response, JwtErrorCode jwtErrorCode)
        throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(jwtErrorCode.getHttpStatus().value());

        ErrorResponse errorResponse = ErrorResponse.of(jwtErrorCode.getErrorCode(),
            jwtErrorCode.getMessage());

        String result = new ObjectMapper().writeValueAsString(errorResponse);
        response.getWriter().write(result);
    }
}