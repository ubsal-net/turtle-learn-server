package com.dsu.turtlelearnserver.randomquestion.repository;

import com.dsu.turtlelearnserver.randomquestion.domain.RandomQuestionSelection;
import org.springframework.data.repository.ListCrudRepository;

public interface RandomQuestionSelectionRepository
    extends ListCrudRepository<RandomQuestionSelection, Long> {

}
