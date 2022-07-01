package ru.rozvezev.springsecurityfirstapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rozvezev.springsecurityfirstapp.services.PeopleService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PeopleService peopleService;

    public AdminController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String adminPage(Model model){
        model.addAttribute("people", peopleService.getAll());
        return "/admin/users_view";
    }
}
