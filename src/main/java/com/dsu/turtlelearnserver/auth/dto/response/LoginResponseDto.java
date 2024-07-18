package com.dsu.turtlelearnserver.auth.dto.response;

public record LoginResponseDto(
    String username,
    String name,
    String accessToken
) {

}
