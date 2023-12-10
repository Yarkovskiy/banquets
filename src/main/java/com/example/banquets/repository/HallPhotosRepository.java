package com.example.banquets.repository;

import com.example.banquets.model.HallPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallPhotosRepository extends JpaRepository<HallPhoto, Long> {

    List<HallPhoto> getAllByHallId(Long id);

    void deleteAllByHallId(Long id);

}
