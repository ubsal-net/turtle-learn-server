package com.dsu.turtlelearnserver.question.dto.response;

import com.dsu.turtlelearnserver.question.domain.Question;
import java.util.List;
import lombok.Builder;

@Builder
public record QuestionInfoForUser(
    long id,
    long number,
    String question,
    String category,
    boolean solved,
    List<SelectionInfo> selections
) {

    public static QuestionInfoForUser of(Question question, boolean userSolved) {
        return QuestionInfoForUser.builder()
            .id(question.getId())
            .number(question.getNumber())
            .question(question.getQuestion())
            .category(question.getCategoryName())
            .solved(userSolved)
            .selections(question.getSelections().stream().map(SelectionInfo::from).toList())
            .build();
    }
}
