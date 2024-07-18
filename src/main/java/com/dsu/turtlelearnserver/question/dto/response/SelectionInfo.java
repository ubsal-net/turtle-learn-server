package com.dsu.turtlelearnserver.question.dto.response;

import com.dsu.turtlelearnserver.question.domain.Selection;

public record SelectionInfo(
    long id,
    String content
) {

    public static SelectionInfo from(Selection selection) {
        return new SelectionInfo(
            selection.getId(),
            selection.getContent()
        );
    }
}
