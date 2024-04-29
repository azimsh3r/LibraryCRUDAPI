package com.example.bookstore.repositories;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByFullName(String fullName);

    @Query("from Book where person_id = :person_id")
    List<Book> findBooksById(@Param("person_id") int id);
}
