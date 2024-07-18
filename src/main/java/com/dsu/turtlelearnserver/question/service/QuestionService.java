package com.dsu.turtlelearnserver.question.service;

import com.dsu.turtlelearnserver.question.domain.AnswerSubmission;
import com.dsu.turtlelearnserver.question.domain.Question;
import com.dsu.turtlelearnserver.question.domain.Selection;
import com.dsu.turtlelearnserver.question.dto.response.CategoryInfo;
import com.dsu.turtlelearnserver.question.repository.CategoryRepository;
import com.dsu.turtlelearnserver.question.repository.SelectionRepository;
import com.dsu.turtlelearnserver.question.dto.request.AnswerSubmissionForm;
import com.dsu.turtlelearnserver.question.dto.response.AnswerSubmissionResponse;
import com.dsu.turtlelearnserver.question.dto.response.CategoriesResponse;
import com.dsu.turtlelearnserver.question.dto.response.QuestionInfo;
import com.dsu.turtlelearnserver.question.dto.response.QuestionInfoForUser;
import com.dsu.turtlelearnserver.question.dto.response.QuestionResponse;
import com.dsu.turtlelearnserver.question.repository.AnswerSubmissionRepository;
import com.dsu.turtlelearnserver.question.repository.QuestionRepository;
import com.dsu.turtlelearnserver.user.domain.User;
import com.dsu.turtlelearnserver.user.repository.UserRepository;
import java.util.List;
import com.dsu.turtlelearnserver.question.domain.Category;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerSubmissionRepository answerSubmissionRepository;
    private final UserRepository userRepository;
    private final SelectionRepository selectionRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public QuestionResponse getQuestionsForUser(Long categoryId, String username) {
        User user = findUser(username);

        Category category = getCategoryById(categoryId);

        var subMap = getAnswerSubmittedQuestionMap(user);
        List<Question> questions = getQuestionsByCategory(category);

        List<QuestionInfoForUser> questionInfoList = questions.stream()
            .map(question -> QuestionInfoForUser.of(question, subMap.containsKey(question)))
            .toList();

        return new QuestionResponse(questionInfoList);
    }

    @Transactional(readOnly = true)
    public QuestionInfo getQuestionById(long questionId) {
        return questionRepository.findById(questionId)
            .map(QuestionInfo::from)
            .orElseThrow(
                () -> new NoSuchElementException("해당 Question을 찾을 수 없습니다 : " + questionId)
            );
    }

    @Transactional
    public AnswerSubmissionResponse createSubmission(
        AnswerSubmissionForm form,
        String username
    ) {
        User user = findUser(username);
        Selection selection = selectionRepository.findById(form.selectionId())
            .orElseThrow(() -> new NoSuchElementException(
                "해당 selection을 찾을 수 없습니다 : " + form.selectionId()
            ));
        AnswerSubmission submission = answerSubmissionRepository.save(
            new AnswerSubmission(user, selection)
        );

        return AnswerSubmissionResponse.from(submission);
    }

    public CategoriesResponse getCategoryList() {
        return new CategoriesResponse(
            categoryRepository.findAll().stream().map(CategoryInfo::from).toList()
        );
    }

    private User findUser(String username) {
        return userRepository.findUserByUsername(username)
            .orElseThrow(() -> new NoSuchElementException("해당 유저를 찾을 수 없습니다 : " + username));
    }

    private Map<Question, List<AnswerSubmission>> getAnswerSubmittedQuestionMap(User user) {
        return answerSubmissionRepository.findByUser(user)
            .stream().collect(Collectors.groupingBy(
                AnswerSubmission::getQuestion
            ));
    }

    private Category getCategoryById(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        return categoryRepository.findById(categoryId)
            .orElseThrow(() -> new NoSuchElementException("해당 카테고리를 조회할 수 없습니다 : " + categoryId));
    }

    private List<Question> getQuestionsByCategory(Category category) {
        if (category == null) {
            return questionRepository.findAll();
        }
        return questionRepository.findAllByCategory(category);
    }
}
