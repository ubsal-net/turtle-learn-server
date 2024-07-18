package com.dsu.turtlelearnserver.question.dto.response;

import com.dsu.turtlelearnserver.question.domain.Question;
import java.util.List;

public record QuestionInfo(
    long id,
    long number,
    String category,
    String question,
    List<SelectionInfo> selections
) {

    public static QuestionInfo from(Question question) {
        return new QuestionInfo(
            question.getId(),
            question.getNumber(),
            question.getCategoryName(),
            question.getQuestion(),
            question.getSelections().stream().map(SelectionInfo::from).toList()
        );
    }
}
