package com.dsu.turtlelearnserver.question.repository;

import com.dsu.turtlelearnserver.question.domain.RandomQuestionSelection;
import org.springframework.data.repository.ListCrudRepository;

public interface RandomQuestionSelectionRepository
    extends ListCrudRepository<RandomQuestionSelection, Long> {

}
