package com.dsu.turtlelearnserver.question.repository;

import com.dsu.turtlelearnserver.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDataRepository extends JpaRepository<Question, Long> {

}

