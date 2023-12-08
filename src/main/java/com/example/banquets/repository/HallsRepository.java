package com.example.banquets.repository;

import com.example.banquets.model.BanquetHall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallsRepository extends JpaRepository<BanquetHall, Long> {
}
