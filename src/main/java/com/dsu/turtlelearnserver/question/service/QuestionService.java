package com.dsu.turtlelearnserver.question.service;

import com.dsu.turtlelearnserver.question.domain.Category;
import com.dsu.turtlelearnserver.question.dto.response.CategoriesResponse;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    public CategoriesResponse getCategoryList() {
        return new CategoriesResponse(
            Arrays.stream(Category.values()).map(Category::name).toList()
        );
    }
}
