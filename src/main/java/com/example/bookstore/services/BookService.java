package com.example.bookstore.services;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.Person;
import com.example.bookstore.repositories.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll(Integer pageIndex, Integer books) {
        return bookRepository.findAll(PageRequest.of(pageIndex != null ? pageIndex : 0, books != null ? books : 10, Sort.by("year").ascending())).getContent();
    }

    @Transactional
    public void edit(int id, Book book) {
        book.setBook_id(id);
        book.setBorrowedAt(new Date());
        bookRepository.save(book);
        bookRepository.assignPerson_idById(book.getPerson_id(), id);
    }

    @Transactional
    public void add(Book book) {
        book.setBorrowedAt(new Date());
        bookRepository.save(book);
        System.out.println("It is saved");
        bookRepository.assignPerson_idById(book.getPerson_id(), book.getBook_id());
        System.out.println("IT is assigned");
    }

    public Optional<Book> get(int id) {
        return bookRepository.findById(id);
    }

    public Optional<Person> getBorrower(int id) {
        return Optional.ofNullable(bookRepository.findById(id).get().getBorrower());
    }

    public List<Person> getAllPeople() {
        return bookRepository.findAllPeople();
    }

    @Transactional
    public void freeBook(int id) {
        bookRepository.assignPerson_idById(null, id);
    }

    @Transactional
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findBooksBySearched(String searched) {
        return bookRepository.findByNameStartingWith(searched);
    }
}
