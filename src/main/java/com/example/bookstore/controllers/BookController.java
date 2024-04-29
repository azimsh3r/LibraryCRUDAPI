package com.example.bookstore.controllers;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.Person;
import com.example.bookstore.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "books_per_page", required = false) Integer books) {
        model.addAttribute("books", bookService.getAll(page, books));
        return "book/index";
    }

    @GetMapping("/search")
    public String searchPage(@ModelAttribute("searched") String searched) {
        return "book/search";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute("searched") String searched, Model model) {
        model.addAttribute("books", bookService.findBooksBySearched(searched));
        return "book/search";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.get(id).get());
        Optional<Person> bookBorrower = bookService.getBorrower(id);

        model.addAttribute("borrower", bookBorrower);
        model.addAttribute("people", bookService.getAllPeople());
        return "book/show";
    }

    @GetMapping("/new")
    public String addBook(@ModelAttribute("book") Book book, Model model) {
        model.addAttribute("people", bookService.getAllPeople());
        return "book/new";
    }

    @PostMapping
    public String add(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "book/new";
        }
        bookService.add(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.get(id).get());
        model.addAttribute("people",bookService.getAllPeople());
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) {
            return "book/edit";
        }
        bookService.edit(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String release(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookService.edit(id, book);
        return "redirect:/books/"+id;
    }

    @PostMapping("/{id}/free")
    public String free(@PathVariable("id") int id) {
        bookService.freeBook(id);
        return "redirect:/books/"+id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
