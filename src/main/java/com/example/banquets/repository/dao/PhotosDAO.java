package com.example.banquets.repository.dao;

import com.example.banquets.model.HallPhoto;
import com.example.banquets.repository.PhotosRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Repository
public class PhotosDAO {

    private PhotosRepository photosRepository;

    public PhotosDAO(PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
    }

    public HallPhoto save(HallPhoto photo) {
        return photosRepository.save(photo);
    }

    public List<HallPhoto> getAllByHallId(Long id) {
        return photosRepository.getAllByHallId(id);
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


}
