package com.dsu.turtlelearnserver.question.controller;

import com.dsu.turtlelearnserver.question.domain.Category;
import com.dsu.turtlelearnserver.question.dto.response.CategoriesResponse;
import com.dsu.turtlelearnserver.question.dto.response.QuestionResponse;
import com.dsu.turtlelearnserver.question.service.QuestionService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        @RequestParam(required = false) Category category,
        Principal principal
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(questionService.getQuestionsForUser(category, principal.getName()));
    }

    @GetMapping("categories")
    public ResponseEntity<CategoriesResponse> getCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getCategoryList());
    }
}
