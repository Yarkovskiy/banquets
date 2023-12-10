package com.example.banquets.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dish_photos")
public class DishPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dish_id")
    private long dishId;

    @Column(name = "photo_url")
    private String photoUrl;

    public DishPhoto(long id, long dishId, String photoUrl) {
        this.id = id;
        this.dishId = dishId;
        this.photoUrl = photoUrl;
    }

    public DishPhoto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDishId() {
        return dishId;
    }

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
