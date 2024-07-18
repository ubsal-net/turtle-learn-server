package com.dsu.turtlelearnserver.question.repository;

import com.dsu.turtlelearnserver.question.domain.RandomQuestion;
import org.springframework.data.repository.ListCrudRepository;

public interface RandomQuestionRepository extends ListCrudRepository<RandomQuestion, Long> {

}
