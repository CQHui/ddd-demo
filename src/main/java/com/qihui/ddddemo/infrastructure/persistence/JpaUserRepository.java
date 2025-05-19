package com.qihui.ddddemo.infrastructure.persistence;

import com.qihui.ddddemo.domain.user.User;
import com.qihui.ddddemo.domain.user.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {
    Optional<User> findByUsername(String username);
} 