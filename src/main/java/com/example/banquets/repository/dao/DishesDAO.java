package com.example.banquets.repository.dao;

import com.example.banquets.model.Dish;
import com.example.banquets.repository.DishesRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DishesDAO {

    private DishesRepository dishesRepository;

    public DishesDAO(DishesRepository dishesRepository) {
        this.dishesRepository = dishesRepository;
    }

    public Dish save(Dish dish) {
        return dishesRepository.save(dish);
    }

    public List<Dish> getAllByManagerId(Long id) {
        return dishesRepository.getAllByManagerId(id);
    }

    public Dish getById(Long id) {
        return dishesRepository.getById(id);
    }

    public void deleteById(Long id) {
        dishesRepository.deleteById(id);
    }

}
