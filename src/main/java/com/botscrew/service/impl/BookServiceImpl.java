package com.botscrew.service.impl;

import com.botscrew.domain.Book;
import com.botscrew.repository.BookRepository;
import com.botscrew.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        return (List<Book>) bookRepository.findAll();
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
    public List<Book> findBooks(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}
