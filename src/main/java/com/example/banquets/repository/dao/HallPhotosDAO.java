package com.example.banquets.repository.dao;

import com.example.banquets.model.HallPhoto;
import com.example.banquets.repository.HallPhotosRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Repository
public class HallPhotosDAO {

    private HallPhotosRepository hallPhotosRepository;


    public HallPhotosDAO(HallPhotosRepository hallPhotosRepository) {
        this.hallPhotosRepository = hallPhotosRepository;
    }

    public HallPhoto save(HallPhoto photo) {
        return hallPhotosRepository.save(photo);
    }

    public List<HallPhoto> getAllByHallId(Long id) {
        return hallPhotosRepository.getAllByHallId(id);
    }

    public void deleteAllByHallId(Long id) {
        hallPhotosRepository.deleteAllByHallId(id);
    }

    public void savePhoto(long hallId, MultipartFile photo) {
        try {
            Path uploadPath = Path.of("src/main/resources/static/images/hall_photos/");
            Path filePath = uploadPath.resolve(hallId + "_" + photo.getOriginalFilename());

            Files.copy(photo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            HallPhoto hallPhoto = new HallPhoto();
            hallPhoto.setHallId(hallId);
            hallPhoto.setPhotoUrl("/images/hall_photos/" + filePath.getFileName());
            save(hallPhoto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePhotos(long hallId) {
        try {
            List<HallPhoto> photos = getAllByHallId(hallId);

            for(HallPhoto photo : photos) {
                Path filePath = Path.of("src/main/resources/static", photo.getPhotoUrl());
                Files.deleteIfExists(filePath);
            }

            deleteAllByHallId(hallId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
