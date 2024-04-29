package com.example.bookstore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
@Table(name="Person")
public class Person {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer person_id;
    @Column(name="name")
    @NotEmpty(message = "This field can't be empty!")
    private String fullName;

    @Column(name="birthdate")
    @NotEmpty(message = "This field can't be empty!")
    // dd.mm.yyyy
    @Pattern(regexp = "\\d{2}\\.\\d{2}\\.\\d{4}", message="dd.mm.yyyy")
    private String birthDate;

    @NotEmpty(message = "This field cannot be empty")
    @Column(name="password")
    private String password;

    @Column(name="role")
    private String role;

    @OneToMany(mappedBy="borrower")
    List<Book> books;

    public Person() {}
    public Person(String fullName, String birthDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getPerson_id() {
        return person_id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }
}
