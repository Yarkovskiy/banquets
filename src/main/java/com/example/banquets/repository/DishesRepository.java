package com.example.banquets.repository;

import com.example.banquets.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishesRepository extends JpaRepository<Dish, Long> {

    List<Dish> getAllByManagerId(Long id);

    Dish getById(Long id);

    void deleteById(Long id);

}
