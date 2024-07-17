package com.dsu.turtlelearnserver.question.dto.response;

import com.dsu.turtlelearnserver.question.domain.Category;
import com.dsu.turtlelearnserver.question.domain.Question;
import lombok.Builder;

@Builder
public record QuestionInfoForUser(
    long id,
    long number,
    String question,
    Category category,
    boolean solved
) {

    public static QuestionInfoForUser of(Question question, boolean userSolved) {
        return QuestionInfoForUser.builder()
            .id(question.getId())
            .number(question.getNumber())
            .question(question.getQuestion())
            .category(question.getCategory())
            .solved(userSolved)
            .build();
    }
}
