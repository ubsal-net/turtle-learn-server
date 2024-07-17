package com.dsu.turtlelearnserver.question.service;

import com.dsu.turtlelearnserver.question.domain.AnswerSubmission;
import com.dsu.turtlelearnserver.question.domain.Category;
import com.dsu.turtlelearnserver.question.domain.Question;
import com.dsu.turtlelearnserver.question.dto.response.CategoriesResponse;
import com.dsu.turtlelearnserver.question.dto.response.QuestionInfoForUser;
import com.dsu.turtlelearnserver.question.dto.response.QuestionResponse;
import com.dsu.turtlelearnserver.question.repository.AnswerSubmissionRepository;
import com.dsu.turtlelearnserver.question.repository.QuestionRepository;
import com.dsu.turtlelearnserver.user.domain.User;
import com.dsu.turtlelearnserver.user.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerSubmissionRepository answerSubmissionRepository;
    private final UserRepository userRepository;

    public QuestionResponse getQuestionsForUser(Category category, String username) {
        User user = userRepository.findUserByUsername(username)
            .orElseThrow(() -> new NoSuchElementException("해당 유저를 찾을 수 없습니다 : " + username));

        Map<Question, Long> subMap = getAnswerSubmittedQuestionMap(user);
        List<Question> questions = category != null ?
            questionRepository.findAllByCategory(category) :
            questionRepository.findAll();

        return new QuestionResponse(
            questions.stream()
                .map(question -> QuestionInfoForUser.of(question, subMap.containsKey(question)))
                .toList()
        );
    }

    public CategoriesResponse getCategoryList() {
        return new CategoriesResponse(
            Arrays.stream(Category.values()).map(Category::name).toList()
        );
    }

    private Map<Question, Long> getAnswerSubmittedQuestionMap(User user) {
        return answerSubmissionRepository.findByUser(user)
            .stream().collect(Collectors.toMap(
                AnswerSubmission::getQuestion,
                submission -> submission.getQuestion().getId()
            ));
    }
}
