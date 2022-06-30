package ru.rozvezev.springsecurityfirstapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.rozvezev.springsecurityfirstapp.repositories.PeopleRepository;
import ru.rozvezev.springsecurityfirstapp.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PersonDetails> personDetails = peopleRepository.findByUsername(username).map(PersonDetails::new);
        return personDetails.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
