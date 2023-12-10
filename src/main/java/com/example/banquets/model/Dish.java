package com.example.banquets.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "manager_id")
    private long managerId;

    private String name;

    private String description;

    private BigDecimal price;

    public Dish(long id,
                long managerId,
                String name,
                String description,
                BigDecimal price) {
        this.id = id;
        this.managerId = managerId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Dish() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
