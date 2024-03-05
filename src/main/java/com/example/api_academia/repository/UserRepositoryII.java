package com.example.api_academia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api_academia.model.User;

public interface UserRepositoryII extends JpaRepository<User, Long> {
    User findByLogin(String login);
    List<User> findByActiveTrue();
}
