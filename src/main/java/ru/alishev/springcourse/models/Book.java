package ru.alishev.springcourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;
    @NotEmpty(message = "Введите название книги")
    @Column(name = "name")
    private String name;
    @Pattern(regexp = "[А-Я][а-я]+\\s[А-Я][а-я]+",message = "Ваше ФИО дожно выглядеть так: Иванов Иван Иванович")
    @Column(name = "author")
    private String author;
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Person owner;

    public Book(){}

    public Book(int bookId, String name, String author, int year) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int id) {
        this.bookId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
