package com.example.banquets.repository.dao;

import com.example.banquets.model.DishPhoto;
import com.example.banquets.repository.DishPhotosRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Repository
public class DishPhotosDAO {

    DishPhotosRepository dishPhotosRepository;

    public DishPhotosDAO(DishPhotosRepository dishPhotosRepository) {
        this.dishPhotosRepository = dishPhotosRepository;
    }

    public DishPhoto save(DishPhoto photo) {
        return dishPhotosRepository.save(photo);
    }

    public List<DishPhoto> getAllByDishId(Long id) {
        return dishPhotosRepository.getAllByDishId(id);
    }

    public void deleteAllByDishId(Long id) {
        dishPhotosRepository.deleteAllByDishId(id);
    }

    public void savePhoto(long dishId, MultipartFile photo) {
        try {
            Path uploadPath = Path.of("src/main/resources/static/images/dish_photos/");
            Path filePath = uploadPath.resolve(dishId + "_" + photo.getOriginalFilename());

            Files.copy(photo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            DishPhoto dishPhoto = new DishPhoto();
            dishPhoto.setDishId(dishId);
            dishPhoto.setPhotoUrl("/images/dish_photos/" + filePath.getFileName());

            save(dishPhoto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePhotos(long dishId) {
        try {
            List<DishPhoto> photos = getAllByDishId(dishId);

            for(DishPhoto photo : photos) {
                Path filePath = Path.of("src/main/resources/static", photo.getPhotoUrl());
                Files.deleteIfExists(filePath);
            }

            deleteAllByDishId(dishId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
