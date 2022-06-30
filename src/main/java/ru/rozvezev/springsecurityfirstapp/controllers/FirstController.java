package ru.rozvezev.springsecurityfirstapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hello")
    public String sayHello(Model model){
        model.addAttribute("message", "Hello there");
        return "hello_view";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }
}
