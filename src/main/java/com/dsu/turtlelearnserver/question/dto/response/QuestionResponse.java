package com.dsu.turtlelearnserver.question.dto.response;

import java.util.List;

public record QuestionResponse(
    List<QuestionInfoForUser> questions
) {

}
