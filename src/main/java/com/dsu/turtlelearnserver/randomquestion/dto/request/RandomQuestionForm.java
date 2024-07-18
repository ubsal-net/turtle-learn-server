package com.dsu.turtlelearnserver.randomquestion.dto.request;

import com.dsu.turtlelearnserver.question.domain.Category;
import com.dsu.turtlelearnserver.randomquestion.domain.RandomQuestion;
import com.dsu.turtlelearnserver.user.domain.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record RandomQuestionForm(
    @Min(value = 1, message = "카테고리 id는 1 이상이여야 합니다.")
    long categoryId,
    @NotBlank(message = "질문이 비어있습니다.")
    String question,
    List<RandomQuestionSelectionForm> selections
) {

    public RandomQuestion toEntity(User user, Category category) {
        return RandomQuestion.builder()
            .category(category)
            .user(user)
            .question(question)
            .build();
    }
}
