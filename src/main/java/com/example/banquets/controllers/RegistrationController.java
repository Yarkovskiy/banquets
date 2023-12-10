package com.example.banquets.controllers;

import com.example.banquets.model.User;
import com.example.banquets.model.UserRole;
import com.example.banquets.model.sessionData.SessionData;
import com.example.banquets.repository.dao.UsersDAO;
import com.example.banquets.security.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    private UsersDAO usersDAO;
    private SessionData sessionData;


    public RegistrationController(UsersDAO usersDAO,
                                  SessionData sessionData) {
        this.usersDAO = usersDAO;
        this.sessionData = sessionData;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        if (model.containsAttribute("error")) {
            model.addAttribute("errorMessage", model.getAttribute("error"));
        }
        return "register.html";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute User newUser,
                                  @RequestParam("role") String role,
                                  RedirectAttributes redirectAttributes) {

        if (usersDAO.findByEmail(newUser.getEmail()) == null) {

            String redirectPath;

            if (newUser.getEmail().equals("admin@email.com")) {
                newUser.setRole(UserRole.ADMIN);
                redirectPath = "/dashboard"; // todo изменить путь
            }
            else if (role.equals("CUSTOMER")) {
                newUser.setRole(UserRole.CUSTOMER);
                redirectPath = "/dashboard"; // todo изменить путь
            } else {
                newUser.setRole(UserRole.MANAGER);
                redirectPath = "/add-hall";
            }

            // хеширование пароля
            String hashedPassword = PasswordEncoder.hashPassword(newUser.getPassword());
            newUser.setPassword(hashedPassword);

            sessionData.setCurrentUser(usersDAO.save(newUser));

            return "redirect:" + redirectPath;

        }

        redirectAttributes.addFlashAttribute("error",
                "Этот адрес электронной почты уже занят");

        return "redirect:/register";
    }

}
