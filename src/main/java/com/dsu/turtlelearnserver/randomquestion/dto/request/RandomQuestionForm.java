package com.dsu.turtlelearnserver.randomquestion.dto.request;

import com.dsu.turtlelearnserver.randomquestion.domain.RandomQuestion;
import com.dsu.turtlelearnserver.user.domain.User;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record RandomQuestionForm(
    @NotBlank(message = "질문이 비어있습니다.")
    String question,
    List<RandomQuestionSelectionForm> selections
) {

    public RandomQuestion toEntity(User user) {
        return RandomQuestion.builder()
            .user(user)
            .question(question)
            .build();
    }
}
