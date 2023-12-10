package com.example.banquets.repository;

import com.example.banquets.model.BanquetHall;
import com.example.banquets.repository.dao.HallsDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallsRepository extends JpaRepository<BanquetHall, Long> {

    List<BanquetHall> getAllByManagerId(Long manager_id);

    BanquetHall getById(Long id);

    void deleteById(Long id);


}
