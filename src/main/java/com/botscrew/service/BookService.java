package com.botscrew.service;

import com.botscrew.domain.Book;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface BookService {

    Collection<Book> getBooks();

    Book createBook(Book book);

    Book updateBook(Book book);

    Collection<Book> findBooks(String name);

    void deleteBook(Book book);
}
