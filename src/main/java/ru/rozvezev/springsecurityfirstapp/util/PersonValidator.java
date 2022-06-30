package ru.rozvezev.springsecurityfirstapp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.rozvezev.springsecurityfirstapp.models.Person;
import ru.rozvezev.springsecurityfirstapp.services.PeopleService;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Person.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person target = (Person) o;
        Optional<Person> personFromDB = peopleService.findByUsername(target.getUsername());

        if (personFromDB.isPresent())
            errors.rejectValue("username", "", "The username is already exists");
    }
}
