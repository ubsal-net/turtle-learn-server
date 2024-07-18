package com.dsu.turtlelearnserver.question.dto.response;

import com.dsu.turtlelearnserver.question.domain.AnswerSubmission;
import com.dsu.turtlelearnserver.question.domain.Selection;

public record AnswerSubmissionResponse(
    long questionId,
    long selectionId,
    long submissionId,
    boolean answer
) {

    public static AnswerSubmissionResponse from(AnswerSubmission answerSubmission) {
        Selection selection = answerSubmission.getSelection();
        return new AnswerSubmissionResponse(
            selection.getQuestionId(),
            selection.getId(),
            answerSubmission.getId(),
            selection.getAnswer()
        );
    }
}
