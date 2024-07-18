package com.dsu.turtlelearnserver.question.controller;

import com.dsu.turtlelearnserver.question.dto.request.AnswerSubmissionForm;
import com.dsu.turtlelearnserver.question.dto.response.AnswerSubmissionResponse;
import com.dsu.turtlelearnserver.question.dto.response.CategoriesResponse;
import com.dsu.turtlelearnserver.question.dto.response.QuestionInfo;
import com.dsu.turtlelearnserver.question.dto.response.QuestionResponse;
import com.dsu.turtlelearnserver.question.service.QuestionService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<QuestionResponse> getQuestions(
        @RequestParam(required = false) Long categoryId,
        Principal principal
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(questionService.getQuestionsForUser(categoryId, principal.getName()));
    }

    @GetMapping("{questionId}")
    public ResponseEntity<QuestionInfo> getQuestion(@PathVariable long questionId) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(questionService.getQuestionById(questionId));
    }

    @PostMapping("submissions")
    public ResponseEntity<AnswerSubmissionResponse> submitAnswer(
        @RequestBody AnswerSubmissionForm form,
        Principal principal
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(questionService.createSubmission(form, principal.getName()));
    }

    @GetMapping("categories")
    public ResponseEntity<CategoriesResponse> getCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getCategoryList());
    }
}
