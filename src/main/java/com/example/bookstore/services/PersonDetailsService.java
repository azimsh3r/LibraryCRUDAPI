package com.example.bookstore.services;

import com.example.bookstore.models.Person;
import com.example.bookstore.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonDetailsService implements UserDetailsService {
    private final PersonService personService;

    public PersonDetailsService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personService.getByName(username);

        if(person.isEmpty()) {
            throw new UsernameNotFoundException("User is not found!");
        }

        return new PersonDetails(person.get());
    }
}
