package com.dsu.turtlelearnserver.question.dto.response;

import com.dsu.turtlelearnserver.question.domain.RandomQuestion;
import com.dsu.turtlelearnserver.question.domain.RandomQuestionSelection;
import java.util.List;

public record RandomQuestionInfo(
    long id,
    String question,
    String category,
    List<RandomQuestionSelectionInfo> selections
) {

    public static RandomQuestionInfo of(
        RandomQuestion randomQuestion,
        List<RandomQuestionSelection> selections
    ) {
        return new RandomQuestionInfo(
            randomQuestion.getId(),
            randomQuestion.getQuestion(),
            randomQuestion.getCategoryName(),
            selections.stream().map(RandomQuestionSelectionInfo::from).toList()
        );
    }

    public static RandomQuestionInfo from(RandomQuestion randomQuestion) {
        return new RandomQuestionInfo(
            randomQuestion.getId(),
            randomQuestion.getQuestion(),
            randomQuestion.getCategoryName(),
            randomQuestion.getRandomQuestionSelections().stream()
                .map(RandomQuestionSelectionInfo::from)
                .toList()
        );
    }
}
