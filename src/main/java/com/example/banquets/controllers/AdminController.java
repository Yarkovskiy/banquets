package com.example.banquets.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/user-list")
    public String getUsersPage() {


        return null;
    }


}
