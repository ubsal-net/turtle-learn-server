package com.dsu.turtlelearnserver.user.service;

import com.dsu.turtlelearnserver.user.dto.request.RegistrationForm;
import com.dsu.turtlelearnserver.user.dto.response.RegistrationResponse;
import com.dsu.turtlelearnserver.user.exception.DuplicatedUsernameException;
import com.dsu.turtlelearnserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RegistrationResponse createUser(RegistrationForm request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new DuplicatedUsernameException("중복된 username : " + request.username());
        }

        return RegistrationResponse.from(userRepository.save(request.toEntity(passwordEncoder)));
    }
}
