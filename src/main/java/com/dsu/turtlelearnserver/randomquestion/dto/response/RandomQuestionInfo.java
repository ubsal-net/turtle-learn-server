package com.dsu.turtlelearnserver.randomquestion.dto.response;

import com.dsu.turtlelearnserver.randomquestion.domain.RandomQuestion;
import com.dsu.turtlelearnserver.randomquestion.domain.RandomQuestionSelection;
import java.util.List;

public record RandomQuestionInfo(
    long id,
    String question,
    List<RandomQuestionSelectionInfo> selections
) {

    public static RandomQuestionInfo of(
        RandomQuestion randomQuestion,
        List<RandomQuestionSelection> selections
    ) {
        return new RandomQuestionInfo(
            randomQuestion.getId(),
            randomQuestion.getQuestion(),
            selections.stream().map(RandomQuestionSelectionInfo::from).toList()
        );
    }

    public static RandomQuestionInfo from(RandomQuestion randomQuestion) {
        return new RandomQuestionInfo(
            randomQuestion.getId(),
            randomQuestion.getQuestion(),
            randomQuestion.getRandomQuestionSelections().stream()
                .map(RandomQuestionSelectionInfo::from)
                .toList()
        );
    }
}
