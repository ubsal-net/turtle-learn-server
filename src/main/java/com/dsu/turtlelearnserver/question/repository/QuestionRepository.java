package com.dsu.turtlelearnserver.question.repository;

import com.dsu.turtlelearnserver.question.domain.Question;
import org.springframework.data.repository.ListCrudRepository;

public interface QuestionRepository extends ListCrudRepository<Question, Long> {

}
