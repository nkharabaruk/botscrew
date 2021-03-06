package com.botscrew.service.impl;

import com.botscrew.domain.Book;
import com.botscrew.domain.Command;
import com.botscrew.service.ParserService;
import org.springframework.stereotype.Service;

@Service
public class ParserServiceImpl implements ParserService {

    public Command parseCommand(String input) {
        try {
            return Command.valueOf(input.trim().split(" ")[0].trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public Book parseBook(String input) {
        return new Book(parseAuthor(input), parseBookName(input));
    }

    public String parseAuthor(String input) {
        if (input.contains("\"")) {
            return input.substring(parseCommand(input).toString().length(), input.indexOf('"')).trim();
        } else {
            return "Unknown";
        }
    }

    public String parseBookName(String input) {
        if (input.contains("\"")) {
            return input.substring(input.indexOf("\"") + 1, input.lastIndexOf("\"")).trim();
        } else if (input.contains(" ")) {
            return input.substring(input.indexOf(" "), input.length()).trim();
        } else {
            throw new IllegalArgumentException("Incorrect input. ");
        }
    }

    @Override
    public String parseOldBookName(String name) {
        return name.substring(0, name.indexOf(" ") - 1);
    }

    @Override
    public String parseNewBookName(String name) {
        return name.substring(name.indexOf(" ") + 2, name.length());
    }
}
