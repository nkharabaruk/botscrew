package com.botscrew.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    String author;

    public Book() {
    }

    public Book(String author, String name) {
        this.author = author;
        this.name = name;
    }

    @Override
    public String toString() {
        return author + " \"" + name + "\"";
    }
}
