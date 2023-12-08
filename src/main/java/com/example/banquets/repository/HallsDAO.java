package com.example.banquets.repository;

import org.springframework.stereotype.Repository;

@Repository
public class HallsDAO {

    HallsRepository hallsRepository;

    public HallsDAO(HallsRepository hallsRepository) {
        this.hallsRepository = hallsRepository;
    }



}
