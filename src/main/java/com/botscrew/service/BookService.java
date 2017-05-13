package com.botscrew.service;

import com.botscrew.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Book> getBooks();

    Book createBook(Book book);

    Book updateBook(Book book);

    List<Book> findBooks(String name);

    void deleteBook(Book book);
}
