package com.activity.Activity.repository;

import com.activity.Activity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String name);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

}
