package com.botscrew.service.impl;

import com.botscrew.domain.Book;
import com.botscrew.domain.Command;
import com.botscrew.service.ParserService;
import org.springframework.stereotype.Service;

@Service
public class ParserServiceImpl implements ParserService{

    public Command parseCommand(String input) {
        try {
            return Command.valueOf(input.trim().split(" ")[0].trim().toUpperCase());
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Book parseBook(String input) {
        return new Book(parseAuthor(input), parseBookName(input));
    }

    public String parseAuthor(String input) {
        return input.substring(parseCommand(input).toString().length(), input.indexOf('"')).trim();
    }

    public String parseBookName(String input) {
        if (input.contains("\"")) {
            return input.substring(input.indexOf("\"") + 1, input.lastIndexOf("\"")).trim();
        } else {
            return input.substring(parseCommand(input).toString().length()).trim();
        }
    }
}
