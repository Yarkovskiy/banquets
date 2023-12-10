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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private UsersDAO usersDAO;
    private SessionData sessionData;

    public LoginController(UsersDAO usersDAO,
                           SessionData sessionData) {
        this.usersDAO = usersDAO;
        this.sessionData = sessionData;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {

        if (model.containsAttribute("error")) {
            model.addAttribute("errorMessage", model.getAttribute("error"));
        }
        return "login.html";
    }

    @GetMapping("/logout")
    public String getLogout() {

        sessionData.setCurrentUser(null);

        return "login.html";
    }


    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user,
                            RedirectAttributes redirectAttributes) {

        User checkedUser = usersDAO.getUserByEmail(user.getEmail());
        if (checkedUser != null &&
                PasswordEncoder.checkPassword(user.getPassword(),
                        checkedUser.getPassword()
                )) {

            sessionData.setCurrentUser(checkedUser);

            String redirectPath;

            if (checkedUser.getRole() == UserRole.MANAGER) {
                redirectPath = "/add-hall";
            } else {
                redirectPath = "/dashboard";
            }

            return "redirect:" + redirectPath;
        }

        // В случае неудачной авторизации, добавляем сообщение об ошибке в атрибуты редиректа
        redirectAttributes.addFlashAttribute("error", "Неверный логин или пароль");

        // Редирект на страницу авторизации с сообщением об ошибке
        return "redirect:/login";
    }

}
