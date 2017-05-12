package com.botscrew;

import com.botscrew.controller.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

    private final BookController bookController;

    @Autowired
    public LibraryApplication(BookController bookController) {
        this.bookController = bookController;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(LibraryApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.print("Welcome to library! ");
        while (true) {
            bookController.start();
        }
    }
}
