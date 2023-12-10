package com.example.banquets.repository;

import com.example.banquets.model.DishPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishPhotosRepository extends JpaRepository<DishPhoto, Long> {

    List<DishPhoto> getAllByDishId(Long id);

    void deleteAllByDishId(Long id);


}
