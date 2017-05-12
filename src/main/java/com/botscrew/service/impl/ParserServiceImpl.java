package com.botscrew.service.impl;

import com.botscrew.domain.Book;
import com.botscrew.domain.Command;
import com.botscrew.service.ParserService;
import org.springframework.stereotype.Service;

@Service
public class ParserServiceImpl implements ParserService{

    public Command parseCommand(String input) {
        String strCommand = input.split(" ")[0];
        return Command.valueOf(strCommand.trim().toUpperCase());
    }

    public Book parseBook(String input) {
        Book book = new Book();
        try {
            if (input.indexOf(" ") + 1 != input.indexOf("\"")) {
                book.setAuthor(input.substring(input.indexOf(" ") + 1, input.indexOf("\"")));
            }
            String bookName = input.substring(input.indexOf("\"") + 1, input.lastIndexOf("\""));
            if (bookName.contains("\"")) {
                String oldName = bookName.trim().split(" ")[0];
                String newName = bookName.trim().split(" ")[1];
            } else {
                book.setName(input.substring(input.indexOf("\"") + 1, input.lastIndexOf("\"")));
            }
            return book;
        } catch (StringIndexOutOfBoundsException ex) {
            book = null;
        }
        return null;
    }
}
