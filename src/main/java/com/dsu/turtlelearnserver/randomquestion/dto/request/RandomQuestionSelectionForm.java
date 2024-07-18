package com.dsu.turtlelearnserver.randomquestion.dto.request;

import com.dsu.turtlelearnserver.randomquestion.domain.RandomQuestion;
import com.dsu.turtlelearnserver.randomquestion.domain.RandomQuestionSelection;
import jakarta.validation.constraints.NotBlank;

public record RandomQuestionSelectionForm(
    boolean answer,
    @NotBlank(message = "항목 내용이 비어있습니다.")
    String content
) {

    public RandomQuestionSelection toEntity(RandomQuestion randomQuestion) {
        return RandomQuestionSelection.builder()
            .answer(answer)
            .content(content)
            .randomQuestion(randomQuestion)
            .build();
    }
}
