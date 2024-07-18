package com.dsu.turtlelearnserver.question.dto.response;


import com.dsu.turtlelearnserver.question.domain.RandomQuestionSelection;

public record RandomQuestionSelectionInfo(
    boolean answer,
    boolean selected,
    String content
) {

    public static RandomQuestionSelectionInfo from(RandomQuestionSelection selection) {
        return new RandomQuestionSelectionInfo(
            selection.isAnswer(),
            selection.isUserSelected(),
            selection.getContent()
        );
    }
}
