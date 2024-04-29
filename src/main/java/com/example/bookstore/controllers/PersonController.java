package com.example.bookstore.controllers;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.Person;
import com.example.bookstore.services.PersonService;
import com.example.bookstore.utils.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final PersonValidator personValidator;

    @Autowired
    PersonController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personService.getAll());
        return "person/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        List<Book> books = personService.getBooksByBorrower(id);
        if(!books.isEmpty())
            model.addAttribute("books", books);
        model.addAttribute("person", personService.get(id));
        return "person/show";
    }

    @GetMapping("/new")
    public String addPerson(@ModelAttribute("person") Person person) {
        return "person/new";
    }

    @PostMapping
    public String add(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, Model model) {
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()) {
            return "person/new";
        }
        personService.add(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personService.get(id).get());
        return "person/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        person.setPerson_id(id);
        if(bindingResult.hasErrors()) {
            return "person/edit";
        }
        personService.edit(id, person);
        return "redirect:/people";
    }
}