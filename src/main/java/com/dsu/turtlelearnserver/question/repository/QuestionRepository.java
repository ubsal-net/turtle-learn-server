package com.dsu.turtlelearnserver.question.repository;

import com.dsu.turtlelearnserver.question.domain.Category;
import com.dsu.turtlelearnserver.question.domain.Question;
import java.util.List;
import org.springframework.data.repository.ListCrudRepository;

public interface QuestionRepository extends ListCrudRepository<Question, Long> {

    List<Question> findAllByCategory(Category category);
}
