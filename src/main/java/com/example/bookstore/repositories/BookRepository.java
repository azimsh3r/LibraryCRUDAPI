package com.example.bookstore.repositories;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
    @Query("from Person")
    List<Person> findAllPeople();

    @Modifying
    @Query("update Book set person_id = :person_id where book_id = :book_id")
    void assignPerson_idById(@Param("person_id") Integer personId, @Param("book_id") int book_id);


    Page<Book> findAll (Pageable pageable);
    List<Book> findAll(Sort sort);

    @Query("from Book where name like :searched%")
    List<Book> findByNameStartingWith(@Param("searched") String searched);
}