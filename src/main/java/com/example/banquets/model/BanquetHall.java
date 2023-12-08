package com.example.banquets.model;

import jakarta.persistence.*;

@Entity
@Table(name = "halls")
public class BanquetHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int capacity;
    private String description;

    @Column(name = "manager_id")
    private long managerId;

    public BanquetHall(long id, String name, int capacity, String description, long managerId) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.description = description;
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

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }
}
