package com.example.banquets.model;

import jakarta.persistence.*;


@Entity
@Table(name = "hall_photos")
public class HallPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "hall_id")
    private long hallId;

    @Column(name = "photo_url")
    private String photoUrl;

    public HallPhoto(long id, long hallId, String photoUrl) {
        this.id = id;
        this.hallId = hallId;
        this.photoUrl = photoUrl;
    }

    public HallPhoto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHallId() {
        return hallId;
    }

    public void setHallId(long hallId) {
        this.hallId = hallId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
