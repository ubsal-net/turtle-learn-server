package com.dsu.turtlelearnserver.user.dto.response;

import com.dsu.turtlelearnserver.user.domain.Sex;
import com.dsu.turtlelearnserver.user.domain.User;

public record RegistrationResponse(
    String username,
    int age,
    Sex sex
) {

    public static RegistrationResponse from(User user) {
        return new RegistrationResponse(
            user.getUsername(),
            user.getAge(),
            user.getSex()
        );
    }
}
