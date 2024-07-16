package com.dsu.turtlelearnserver.user.repository;

import com.dsu.turtlelearnserver.user.domain.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
}
