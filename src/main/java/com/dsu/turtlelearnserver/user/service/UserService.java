package com.dsu.turtlelearnserver.user.service;

import com.dsu.turtlelearnserver.question.domain.AnswerSubmission;
import com.dsu.turtlelearnserver.question.domain.Question;
import com.dsu.turtlelearnserver.question.repository.AnswerSubmissionRepository;
import com.dsu.turtlelearnserver.user.domain.User;
import com.dsu.turtlelearnserver.user.dto.request.RegistrationForm;
import com.dsu.turtlelearnserver.user.dto.response.RegistrationResponse;
import com.dsu.turtlelearnserver.user.dto.response.UserInfo;
import com.dsu.turtlelearnserver.user.exception.DuplicatedUsernameException;
import com.dsu.turtlelearnserver.user.repository.UserRepository;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AnswerSubmissionRepository answerSubmissionRepository;

    @Transactional
    public RegistrationResponse createUser(RegistrationForm request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new DuplicatedUsernameException("중복된 username : " + request.username());
        }

        return RegistrationResponse.from(userRepository.save(request.toEntity(passwordEncoder)));
    }

    @Transactional(readOnly = true)
    public UserInfo getUserInfo(String username) {
        User user = userRepository.findUserByUsername(username)
            .orElseThrow(() -> new NoSuchElementException("해당 유저를 조회할 수 없습니다 : " + username));

        Map<Question, List<AnswerSubmission>> subMap = answerSubmissionRepository.findByUser(user)
            .stream()
            .collect(Collectors.groupingBy(AnswerSubmission::getQuestion));

        return UserInfo.of(
            user,
            calculateAccuracy(subMap),
            subMap.size()
        );
    }

    private double calculateAccuracy(Map<Question, List<AnswerSubmission>> subMap) {
        int qCount = subMap.size();
        if (qCount < 1) {
            return 0;
        }

        long aCount = subMap.values().stream()
            .map(list -> list.get(list.size() - 1))
            .filter(AnswerSubmission::isAnswer)
            .count();

        return ((double) aCount / qCount) * 100.0;
    }
}
