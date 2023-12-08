package com.example.banquets.repository;

import com.example.banquets.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersDAO {

    UsersRepository usersRepository;

    public UsersDAO(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public void save(User user) {
        usersRepository.save(user);
    }

    public User findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public boolean existsByEmailAndPassword(User user) {
        return usersRepository.existsByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    public User getUserByEmail(String email) {
        return usersRepository.getUserByEmail(email);
    }

}
