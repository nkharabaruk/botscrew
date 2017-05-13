package com.botscrew.controller;

import com.botscrew.domain.Book;
import com.botscrew.domain.Command;
import com.botscrew.service.BookService;
import com.botscrew.service.ParserService;
import org.springframework.stereotype.Controller;

import java.io.InputStreamReader;
import java.util.List;
import java.util.InputMismatchException;
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

    boolean isActive = true;
    Command command = null;

    public void start() {
        greeting();
        while (isActive) {
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            String input = scanner.nextLine().trim();
            command = parserService.parseCommand(input);
            try {
                if (command != null) {
                    doAction(input);
                } else {
                    throw new IllegalArgumentException("No such command! ");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void doAction(String input) {
        switch (command) {
            case ADD:
                addBook(parserService.parseBook(input));
                break;
            case EDIT:
                editBook(parserService.parseBookName(input));
                break;
            case REMOVE:
                removeBook(parserService.parseBookName(input));
                break;
            case ALL:
                showAllBooks();
                break;
            case HELP:
                writeHelp();
                break;
            case EXIT:
                exit();
                break;
            default:
                throw new IllegalArgumentException("Wrong command, try again! ");
        }
    }

    void addBook(Book book) {
        bookService.createBook(book);
        System.out.println("book " + book.toString() + " was added");
    }

    void editBook(String name) {
        try {
            String oldName = parserService.parseOldBookName(name);
            String newName = parserService.parseNewBookName(name);
            Book book = getBook(oldName);
            if (book != null) {
                System.out.println("Book " + book.toString() + " was edited to \"" + newName + "\". ");
                book.setName(newName);
                bookService.updateBook(book);
            }
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Do you mean - edit \"old book`s name\" \"new book`s name\" ?");
        }
    }

    void removeBook(String name) {
        Book book = getBook(name);
        if (book != null) {
            System.out.println("book " + book.toString() + " was removed");
            bookService.deleteBook(book);
        }
    }

    void showAllBooks() {
        List<Book> books = bookService.getBooks();
        if (books.size() == 0) {
            System.out.println("We have not any book.");
        } else {
            System.out.println("Our books:");
            for (Book book : books) {
                System.out.println(book.getAuthor() + " \"" + book.getName() + "\"");
            }
        }
    }

    private Book getBook(String name) {
        List<Book> books = bookService.findBooks(name);
        Book book = null;
        if (books.size() == 0) {
            System.out.println("We have not book with such name. ");
        } else if (books.size() > 1) {
            book = selectOneBook(books);
        } else {
            book = books.get(0);
        }
        return book;
    }

    private Book selectOneBook(List<Book> books) {
        System.out.println("We have few books with such name please choose one by typing a number of book: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i).toString());
        }

        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        try {
            int num = scanner.nextInt();
            if (num < books.size() & num > 0) {
                return books.get(num - 1);
            } else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException ex) {
            System.out.println("Number is not correct. Try again. ");
            return null;
        }
    }

    private void greeting() {
        System.out.println("Welcome to library! What do you want to do?" +
                "\nType 'help' to show allowed commands. ");
    }

    private void writeHelp() {
        System.out.println("Type: " +
                "\n- add *author`s name* *book`s name* - to add book." +
                "\n- edit *old book`s name* *new book`s name* - to edit book." +
                "\n- remove *book`s name* - to delete book." +
                "\n- all books - to show all books." +
                "\n- help - to show hint." +
                "\n- exit - to finish interaction.");
    }

    private void exit() {
        System.out.println("Bye! Come back later.");
        isActive = false;
    }
}
