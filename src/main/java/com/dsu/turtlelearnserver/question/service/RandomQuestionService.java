package com.dsu.turtlelearnserver.question.service;

import com.dsu.turtlelearnserver.question.domain.Category;
import com.dsu.turtlelearnserver.question.domain.RandomQuestion;
import com.dsu.turtlelearnserver.question.dto.request.RandomQuestionForm;
import com.dsu.turtlelearnserver.question.dto.response.RandomQuestionInfo;
import com.dsu.turtlelearnserver.question.repository.CategoryRepository;
import com.dsu.turtlelearnserver.question.repository.RandomQuestionRepository;
import com.dsu.turtlelearnserver.question.repository.RandomQuestionSelectionRepository;
import com.dsu.turtlelearnserver.user.domain.User;
import com.dsu.turtlelearnserver.user.repository.UserRepository;
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

    @Transactional
    public RandomQuestionInfo addRandomQuestion(RandomQuestionForm form, String username) {
        User user = userRepository.findUserByUsername(username)
            .orElseThrow(() -> new NoSuchElementException("해당 유저를 찾을 수 없습니다 : " + username));
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
}
