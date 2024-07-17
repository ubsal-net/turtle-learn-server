package com.dsu.turtlelearnserver.question.controller;

import com.dsu.turtlelearnserver.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;

    // 카테고리 조회

    // question 문제 카테고리 , 유저 선택 -> 문제번호들을 쭉 주고 푼문제인지 아닌지 줘야됨


    // question 문제 카테고리 , 유저 에 문제번호 선택 -> 문제를 줘야됨



    // 총학습한 문제가 몇문제인지
    // 정확도?



}
