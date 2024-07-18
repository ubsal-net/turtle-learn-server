package com.dsu.turtlelearnserver.question.dto.response;

import com.dsu.turtlelearnserver.question.domain.Category;

public record CategoryInfo(
    long id,
    String name
) {

    public static CategoryInfo from(Category category) {
        return new CategoryInfo(category.getId(), category.getName());
    }
}
