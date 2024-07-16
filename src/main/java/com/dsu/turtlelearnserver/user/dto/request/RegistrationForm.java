package com.dsu.turtlelearnserver.user.dto.request;

import com.dsu.turtlelearnserver.user.domain.Sex;
import com.dsu.turtlelearnserver.user.domain.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;

public record RegistrationForm(
    @NotBlank(message = "공백 아이디는 허용되지 않습니다.")
    @Size(min = 5, message = "최소 5자 이상 입력해주세요.")
    String username,
    @Pattern(message = "최소 한개 이상의 대소문자와 숫자, 특수문자를 포함한 8자 이상 16자 이하의 비밀번호를 입력해야 합니다.",
        regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!~$%^=()])(?=\\S+$).{8,16}$")
    String password,
    @Min(value = 1, message = "최소 1 이상의 숫자를 입력해주세요.")
    int age,
    Sex sex
) {

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
            .username(username)
            .password(passwordEncoder.encode(password))
            .age(age)
            .sex(sex)
            .build();
    }
}
