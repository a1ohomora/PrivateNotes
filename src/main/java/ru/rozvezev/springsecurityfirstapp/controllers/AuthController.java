package ru.rozvezev.springsecurityfirstapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rozvezev.springsecurityfirstapp.models.Person;
import ru.rozvezev.springsecurityfirstapp.services.PeopleService;
import ru.rozvezev.springsecurityfirstapp.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final PeopleService peopleService;

    public AuthController(PersonValidator personValidator, PeopleService peopleService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
    }

    @GetMapping("/login")
    public String login(){
        return "/auth/login";
    }


    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person){
        return "/auth/registration";
    }
    
    @PostMapping("/registration")
    public String register(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) return "/auth/registration";

        peopleService.register(person);

        return "redirect:/auth/login";
    }
    
    

}
