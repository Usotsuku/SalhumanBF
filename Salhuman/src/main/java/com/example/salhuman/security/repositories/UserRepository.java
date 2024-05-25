package com.example.salhuman.security.repositories;

import com.example.salhuman.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    User findByname(String name);
}
