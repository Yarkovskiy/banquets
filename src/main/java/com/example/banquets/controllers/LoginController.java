package com.example.banquets.controllers;

import com.example.banquets.model.User;
import com.example.banquets.repository.UsersDAO;
import com.example.banquets.security.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final UsersDAO usersDAO;

    public LoginController(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {

        // Проверяем наличие сообщения об ошибке в модели и передаем его на страницу
        if (model.containsAttribute("error")) {
            model.addAttribute("errorMessage", model.getAttribute("error"));
        }
        return "login.html";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user,
                                            RedirectAttributes redirectAttributes) {


        if (usersDAO.getUserByEmail(user.getEmail()) != null &&
                PasswordEncoder.checkPassword(user.getPassword(),
                        usersDAO.getUserByEmail(user.getEmail()).getPassword()
                )) {
            // В случае успешной авторизации, делаем редирект
            return "redirect:/dashboard";
        }

        // В случае неудачной авторизации, добавляем сообщение об ошибке в атрибуты редиректа
        redirectAttributes.addFlashAttribute("error", "Неверный логин или пароль");

        // Редирект на страницу авторизации с сообщением об ошибке
        return "redirect:/login";
    }

}
