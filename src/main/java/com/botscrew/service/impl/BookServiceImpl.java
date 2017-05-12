package com.botscrew.service.impl;

import com.botscrew.domain.Book;
import com.botscrew.repository.BookRepository;
import com.botscrew.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Collection<Book> getBooks() {
        return (Collection<Book>) bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Collection<Book> findBooks(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}
