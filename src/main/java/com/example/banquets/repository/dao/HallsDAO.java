package com.example.banquets.repository.dao;

import com.example.banquets.model.BanquetHall;
import com.example.banquets.repository.HallsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HallsDAO {

    HallsRepository hallsRepository;

    public HallsDAO(HallsRepository hallsRepository) {
        this.hallsRepository = hallsRepository;
    }

    public BanquetHall save(BanquetHall hall) {
        return hallsRepository.save(hall);
    }

    public List<BanquetHall> getAllByManagerId(Long manager_id) {
        return hallsRepository.getAllByManagerId(manager_id);
    }

    public BanquetHall getById(Long id) {
        return hallsRepository.getById(id);
    }

}
