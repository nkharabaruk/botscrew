package com.botscrew.controller;

import com.botscrew.domain.Book;
import com.botscrew.domain.Command;
import com.botscrew.service.BookService;
import com.botscrew.service.ParserService;
import org.springframework.stereotype.Controller;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

@Controller
public class BookController {

    private final ParserService parserService;

    private final BookService bookService;

    public BookController(BookService bookService, ParserService parserService) {
        this.bookService = bookService;
        this.parserService = parserService;
    }

    Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public void start() {
        greeting();
        String input = scanner.nextLine().trim();
        try {
            doAction(parserService.parseCommand(input), parserService.parseBook(input));
        } catch (IllegalArgumentException ex) {
            System.out.println("Wrong command, try again!");
        }
    }

    private void doAction(Command command, Book book) {
        switch (command) {
            case ADD: addBook(book); break;
            case EDIT: editBook(book.toString()); break;
            case REMOVE: removeBook(book); break;
            case ALL: showAllBooks(); break;
            case HELP: writeHelp(); break;
            case EXIT: exit(); break;
        }
    }

    void addBook(Book book) {
        bookService.createBook(book);
        System.out.println("book " + book.getAuthor() + " \"" + book.getName() + "\" was added");
    }

    void editBook(String name) {
        List<Book> books = (List<Book>) bookService.findBooks(name);
        Book book = null;
        if (books.size() == 0) {
            System.out.println("We have not book with such name.");
        } else if (books.size() > 1) {
            book = checkOneBook(books);
            System.out.println(book);
        } else {
            book = books.get(0);
        }
        System.out.print("Book " + book.getAuthor() + "\"" + book.getName() + "\" was edited to ");
        bookService.updateBook(book);
        System.out.println(book.getName() + ".");
    }

    void removeBook(Book book) {
        bookService.deleteBook(book);
        System.out.println("book " + book.getAuthor() + "\"" + book.getName() + "\" was removed");
    }

    void showAllBooks() {
        Collection<Book> books = bookService.getBooks();
        if (books.size() == 0) {
            System.out.println("We have not any book.");
        } else {
            System.out.println("Our books:");
            for (Book book : books) {
                System.out.println(book.getAuthor() + " \"" + book.getName() + "\"");
            }
        }
    }

    private Book checkOneBook(List<Book> books) {
        System.out.println("We have few books with such name please choose one by typing a number of book:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i).getAuthor() + " \"" + books.get(i).getName() + "\"");
        }
        int num = scanner.nextInt() - 1;
        if (num > books.size() || num < 1) {
            System.out.println("Number is not correct. Try again.");
        }
        return books.get(num);
    }

    private void greeting() {
        System.out.println("What do you want to do?");
        System.out.println("Type 'help' to show allowed commands.");
    }

    private void writeHelp() {
        System.out.println("Type:");
        System.out.println("1) add *author`s name* *book`s name* - to add book.");
        System.out.println("2) edit *old book`s name* *new book`s name* - to edit book.");
        System.out.println("3) remove *book`s name* - to delete book.");
        System.out.println("4) all books - to show all books.");
        System.out.println("6) help - to show hint.");
        System.out.println("4) exit - to finish interaction.");
    }

    private void exit() {
        System.out.println("Bye! Come back later.");
        System.exit(0);
    }
}
