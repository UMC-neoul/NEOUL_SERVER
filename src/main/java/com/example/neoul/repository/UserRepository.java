package com.example.neoul.repository;

import com.example.neoul.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @EntityGraph(attributePaths = "authorities")
    Optional<User> findUserWithAuthoritiesByUsername(String email);

    Optional<User> findUserByUsername(String email);


    Optional<User> findUserByUserId(Long userId);


    User findByUsernameAndSocial(String username, String social);

    boolean existsByUsernameAndSocial(String username, String social);
}
