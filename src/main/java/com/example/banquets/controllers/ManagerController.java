package com.example.banquets.controllers;

import com.example.banquets.model.BanquetHall;
import com.example.banquets.model.Dish;
import com.example.banquets.model.DishPhoto;
import com.example.banquets.model.HallPhoto;
import com.example.banquets.model.sessionData.SessionData;
import com.example.banquets.repository.DishPhotosRepository;
import com.example.banquets.repository.dao.DishPhotosDAO;
import com.example.banquets.repository.dao.DishesDAO;
import com.example.banquets.repository.dao.HallsDAO;
import com.example.banquets.repository.dao.HallPhotosDAO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ManagerController {

    HallsDAO hallsDAO;
    DishesDAO dishesDAO;
    HallPhotosDAO hallPhotosDAO;
    DishPhotosDAO dishPhotosDAO;

    SessionData sessionData;

    public ManagerController(HallsDAO hallsDAO,
                             DishesDAO dishesDAO,
                             HallPhotosDAO hallPhotosDAO,
                             DishPhotosDAO dishPhotosDAO,
                             SessionData sessionData) {
        this.hallsDAO = hallsDAO;
        this.dishesDAO = dishesDAO;
        this.hallPhotosDAO = hallPhotosDAO;
        this.dishPhotosDAO = dishPhotosDAO;
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
            hallPhotosDAO.savePhoto(hallId, photo);
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

        List<HallPhoto> hallPhotos = hallPhotosDAO.getAllByHallId(id);
        model.addAttribute("hallPhotos", hallPhotos);

        return "hall-page.html";
    }

    @Transactional
    @PostMapping("/delete-hall/{id}")
    public String deleteHall(@PathVariable Long id) {

        hallPhotosDAO.deletePhotos(id);
        hallsDAO.deleteById(id);

        return "redirect:/hall-list";
    }

    @GetMapping("/add-dish")
    public String addDish() {
        return "add-dish-form.html";
    }

    @PostMapping("/add-dish")
    public String addDish(@RequestParam String name,
                          @RequestParam BigDecimal price,
                          @RequestParam String description,
                          @RequestParam("photos") List<MultipartFile> photos) {

        Dish dish = new Dish();
        dish.setName(name);
        dish.setPrice(price);
        dish.setDescription(description);
        dish.setManagerId(sessionData.getCurrentUser().getId());

        long dishId = dishesDAO.save(dish).getId();

        for (MultipartFile photo : photos) {
            dishPhotosDAO.savePhoto(dishId, photo);
        }

        return "redirect:/add-dish";
    }

    @GetMapping("/dish-list")
    public String getDishList(Model model) {
        model.addAttribute("dishes",
                dishesDAO.getAllByManagerId(sessionData.getCurrentUser().getId()));

        return "dish-list.html";
    }

    @GetMapping("/dish/{id}")
    public String viewDish(@PathVariable Long id, Model model) {

        Dish dish = dishesDAO.getById(id);
        model.addAttribute("dish", dish);

        List<DishPhoto> dishPhotos = dishPhotosDAO.getAllByDishId(id);
        model.addAttribute("dishPhotos", dishPhotos);

        return "dish-page.html";
    }

    @Transactional
    @PostMapping("/delete-dish/{id}")
    public String deleteDish(@PathVariable Long id) {

        dishPhotosDAO.deletePhotos(id);
        dishesDAO.deleteById(id);

        return "redirect:/dish-list";
    }


}
