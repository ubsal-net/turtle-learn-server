package com.dsu.turtlelearnserver.question.service;

import com.dsu.turtlelearnserver.question.repository.QuestionDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionDataRepository questionDataRepository;



}
