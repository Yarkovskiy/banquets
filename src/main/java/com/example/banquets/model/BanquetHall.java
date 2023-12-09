package com.example.banquets.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "halls")
public class BanquetHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int capacity;
    private String description;

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "manager_id")
    private long managerId;


    public BanquetHall(long id,
                       String name,
                       int capacity,
                       String description,
                       BigDecimal hourlyRate,
                       long managerId) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.rate = hourlyRate;
        this.managerId = managerId;
    }

    public BanquetHall() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal hourlyRate) {
        this.rate = hourlyRate;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }
}
