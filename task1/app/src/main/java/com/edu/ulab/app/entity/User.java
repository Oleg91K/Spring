package com.edu.ulab.app.entity;

import java.util.ArrayList;
import java.util.List;

public class User extends Entity{
    private String fullName;
    private String title;
    private Integer age;
    private final List<Book> books = new ArrayList<>();


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", title='" + title + '\'' +
                ", age=" + age +
                ", books=" + books +
                '}';
    }
}
