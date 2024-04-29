package com.example.bookstore.services;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.Person;
import com.example.bookstore.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Optional<Person> getByName(String fullName) {
        return personRepository.findByFullName(fullName);
    }

    public List<Book> getBooksByBorrower(int id) {
        return personRepository.findBooksById(id);
    }

    public Optional<Person> get(int id) {
        return personRepository.findById(id);
    }

    @Transactional
    public void add(Person person) {
        person.setRole("ROLE_USER");
        personRepository.save(person);
    }

    @Transactional
    public void edit(int id, Person person) {
        person.setPerson_id(id);
        personRepository.save(person);
    }
}