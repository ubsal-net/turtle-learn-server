package com.dsu.turtlelearnserver.randomquestion.repository;

import com.dsu.turtlelearnserver.randomquestion.domain.RandomQuestion;
import com.dsu.turtlelearnserver.user.domain.User;
import java.util.List;
import org.springframework.data.repository.ListCrudRepository;

public interface RandomQuestionRepository extends ListCrudRepository<RandomQuestion, Long> {

    List<RandomQuestion> findAllByUser(User user);
}
