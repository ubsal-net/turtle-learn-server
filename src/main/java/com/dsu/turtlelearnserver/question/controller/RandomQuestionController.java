package com.dsu.turtlelearnserver.question.controller;

import com.dsu.turtlelearnserver.question.dto.request.RandomQuestionForm;
import com.dsu.turtlelearnserver.question.dto.response.RandomQuestionInfo;
import com.dsu.turtlelearnserver.question.service.RandomQuestionService;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("random-questions")
public class RandomQuestionController {

    private final RandomQuestionService randomQuestionService;

    @GetMapping
    public ResponseEntity<List<RandomQuestionInfo>> getRandomQuestions(
        Principal principal
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(randomQuestionService.getRandomQuestions(principal.getName()));
    }

    @GetMapping("{id}")
    public ResponseEntity<RandomQuestionInfo> getRandomQuestion(
        @PathVariable long id,
        Principal principal
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(randomQuestionService.getRandomQuestion(id, principal.getName()));
    }

    @PostMapping
    public ResponseEntity<RandomQuestionInfo> createRandomQuestion(
        @RequestBody @Valid RandomQuestionForm form,
        Principal principal
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(randomQuestionService.addRandomQuestion(form, principal.getName()));
    }
}
