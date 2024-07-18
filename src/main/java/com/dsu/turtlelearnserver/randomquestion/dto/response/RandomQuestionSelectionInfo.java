package com.dsu.turtlelearnserver.randomquestion.dto.response;


import com.dsu.turtlelearnserver.randomquestion.domain.RandomQuestionSelection;

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
