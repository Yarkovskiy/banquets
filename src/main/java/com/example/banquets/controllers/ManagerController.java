package com.example.banquets.controllers;

import com.example.banquets.model.BanquetHall;
import com.example.banquets.model.HallPhoto;
import com.example.banquets.model.sessionData.SessionData;
import com.example.banquets.repository.dao.HallsDAO;
import com.example.banquets.repository.dao.PhotosDAO;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ManagerController {

    HallsDAO hallsDAO;
    PhotosDAO photosDAO;

    SessionData sessionData;

    public ManagerController(HallsDAO hallsDAO,
                             PhotosDAO photosDAO,
                             SessionData sessionData) {
        this.hallsDAO = hallsDAO;
        this.photosDAO = photosDAO;
        this.sessionData = sessionData;
    }

    @GetMapping("/add-hall")
    public String getHallForm() {
        return "add-hall-form.html";
    }

    @PostMapping("/add-hall")
    public String addHall(@RequestParam String name,
                          @RequestParam int capacity,
                          @RequestParam BigDecimal rate,
                          @RequestParam String description,
                          @RequestParam("photos") List<MultipartFile> photos) {

        BanquetHall hall = new BanquetHall();
        hall.setName(name);
        hall.setCapacity(capacity);
        hall.setRate(rate);
        hall.setDescription(description);
        hall.setManagerId(sessionData.getCurrentUser().getId());

        long hallId = hallsDAO.save(hall).getId();
        for (MultipartFile photo : photos) {
            photosDAO.savePhoto(hallId, photo);
        }

        return "redirect:/add-hall";
    }

    @GetMapping("/hall-list")
    public String getHallList(Model model) {
        model.addAttribute("halls",
                hallsDAO.getAllByManagerId(sessionData.getCurrentUser().getId()));

        return "hall-list.html";
    }

    @GetMapping("/hall/{id}")
    public String viewHall(@PathVariable Long id, Model model) {

        BanquetHall hall = hallsDAO.getById(id);
        model.addAttribute("hall", hall);

        List<HallPhoto> hallPhotos = photosDAO.getAllByHallId(id);
        model.addAttribute("hallPhotos", hallPhotos);

        return "hall-page.html";
    }

    @GetMapping("/delete-hall/{id}")
    public String deleteHall(@PathVariable Long id) {



        return "redirect:/hall-list.html";
    }

}
