package com.dsu.turtlelearnserver.question.repository;

import com.dsu.turtlelearnserver.question.domain.AnswerSubmission;
import com.dsu.turtlelearnserver.user.domain.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AnswerSubmissionRepository extends CrudRepository<AnswerSubmission, Long> {

    List<AnswerSubmission> findByUser(User user);
}
