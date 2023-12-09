package com.example.banquets.repository.dao;

import com.example.banquets.model.User;
import com.example.banquets.repository.UsersRepository;
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

    public User save(User user) {
        return usersRepository.save(user);
    }

    public User findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public User getUserByEmail(String email) {
        return usersRepository.getUserByEmail(email);
    }

}
