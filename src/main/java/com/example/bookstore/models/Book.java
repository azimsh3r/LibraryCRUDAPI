package com.example.bookstore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="book")
public class Book {

    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    @Column(name="person_id", insertable=false, updatable=false)
    private Integer person_id;

    @Column(name="name")
    @Size(min = 2, max = 200, message = "This name is invalid")
    private String name;

    @Column(name="author")
    @NotEmpty(message = "This field cannot be empty")
    private String author;

    @Column(name="year")
    @NotEmpty(message = "This field cannot be empty")
    @Pattern(regexp="\\d{4}", message = "yyyy")
    private String year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person borrower;

    @Column(name="borrowed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date borrowedAt;

    @Transient
    private Boolean expired;

    public Book() {}

    public Person getBorrower() {
        return borrower;
    }

    public void setBorrower(Person borrower) {
        this.borrower = borrower;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getBook_id() {
        return book_id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public Date getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(Date borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public Boolean isExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Boolean getExpiry() {
        if(borrowedAt == null) {
            return false;
        }

        Calendar expiry = Calendar.getInstance();
        expiry.setTime(borrowedAt);
        expiry.add(Calendar.DAY_OF_MONTH, 10);

        if(new Date().after(expiry.getTime()))
            return true;
        else
            return false;
    }
}