package com.example.banquets.repository;


import com.example.banquets.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    User getUserByEmail(String email);
}
