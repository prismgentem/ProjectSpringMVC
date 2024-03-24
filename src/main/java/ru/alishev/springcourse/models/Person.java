package ru.alishev.springcourse.models;

import jakarta.validation.constraints.*;

public class Person {
    private int userId;
    @Pattern(regexp = "[А-Я][а-я]+\\s[А-Я][а-я]+\\s[А-Я][а-я]+",message = "Ваше ФИО дожно выглядеть так: Иванов Иван Иванович")
    private String fullName;
    @Max(value = 2018, message = "Ваш возраст должен быть не меньше 6 лет")
    private int yearOfBirth;

    public Person(){

    }
    public Person(int user_id, String fullName, int yearOfBirth) {
        this.userId = user_id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
