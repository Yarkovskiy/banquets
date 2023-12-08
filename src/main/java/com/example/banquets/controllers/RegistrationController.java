package com.example.banquets.controllers;

import com.example.banquets.model.User;
import com.example.banquets.model.UserRole;
import com.example.banquets.repository.UsersDAO;
import com.example.banquets.security.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    private final UsersDAO usersDAO;


    public RegistrationController(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
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
                                  RedirectAttributes redirectAttributes) {

        if (usersDAO.findByEmail(newUser.getEmail()) == null) {

            // присвоение роли пользователю
            if (newUser.getEmail().equals("admin@email.com"))
                newUser.setRole(UserRole.ADMIN);
            else
                newUser.setRole(UserRole.CUSTOMER);

            // хеширование пароля
            String hashedPassword = PasswordEncoder.hashPassword(newUser.getPassword());
            newUser.setPassword(hashedPassword);

            usersDAO.save(newUser);

            return "redirect:/dashboard";

        }

        redirectAttributes.addFlashAttribute("error",
                "Этот адрес электронной почты уже занят");

        return "redirect:/register";
    }

}
