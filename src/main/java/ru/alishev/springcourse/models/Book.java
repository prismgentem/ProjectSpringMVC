package ru.alishev.springcourse.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class Book {
    private int bookId;
    @NotEmpty(message = "Введите название книги")
    private String name;
    @Pattern(regexp = "[А-Я][а-я]+\\s[А-Я][а-я]+",message = "Ваше ФИО дожно выглядеть так: Иванов Иван Иванович")
    private String author;
    private int year;

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
}
