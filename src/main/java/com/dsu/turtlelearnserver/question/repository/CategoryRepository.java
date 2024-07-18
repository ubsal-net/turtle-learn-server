package com.dsu.turtlelearnserver.question.repository;

import com.dsu.turtlelearnserver.question.domain.Category;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryRepository extends ListCrudRepository<Category, Long> {

}
