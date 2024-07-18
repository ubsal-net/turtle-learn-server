package com.dsu.turtlelearnserver.question.service;

import com.dsu.turtlelearnserver.question.domain.Category;
import com.dsu.turtlelearnserver.question.domain.RandomQuestion;
import com.dsu.turtlelearnserver.question.dto.request.RandomQuestionForm;
import com.dsu.turtlelearnserver.question.dto.response.RandomQuestionInfo;
import com.dsu.turtlelearnserver.question.exception.IllegalQuestionAccessException;
import com.dsu.turtlelearnserver.question.repository.CategoryRepository;
import com.dsu.turtlelearnserver.question.repository.RandomQuestionRepository;
import com.dsu.turtlelearnserver.question.repository.RandomQuestionSelectionRepository;
import com.dsu.turtlelearnserver.user.domain.User;
import com.dsu.turtlelearnserver.user.repository.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RandomQuestionService {

    private final RandomQuestionRepository randomQuestionRepository;
    private final RandomQuestionSelectionRepository randomQuestionSelectionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<RandomQuestionInfo> getRandomQuestions(String username) {
        User user = findUser(username);
        return randomQuestionRepository.findAllByUser(user).stream()
            .map(RandomQuestionInfo::from)
            .toList();
    }

    @Transactional(readOnly = true)
    public RandomQuestionInfo getRandomQuestion(long id, String username) {
        User user = findUser(username);
        RandomQuestion question = randomQuestionRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("해당 RandomQuestion을 찾을 수 없습니다 : " + id));

        if (!question.getUser().equals(user)) {
            throw new IllegalQuestionAccessException("해당 질문에 대한 접근 권한이 없습니다.");
        }

        return RandomQuestionInfo.from(question);
    }

    @Transactional
    public RandomQuestionInfo addRandomQuestion(RandomQuestionForm form, String username) {
        User user = findUser(username);
        Category category = categoryRepository.findById(form.categoryId())
            .orElseThrow(() ->
                new NoSuchElementException("해당 카테고리를 찾을 수 없습니다 : " + form.categoryId()));

        RandomQuestion question = randomQuestionRepository.save(form.toEntity(user, category));

        return RandomQuestionInfo.of(question, randomQuestionSelectionRepository.saveAll(
            form.selections().stream()
                .map(selection -> selection.toEntity(question))
                .toList()
        ));
    }

    private User findUser(String username) {
        return userRepository.findUserByUsername(username)
            .orElseThrow(() -> new NoSuchElementException("해당 유저를 찾을 수 없습니다 : " + username));
    }
}
