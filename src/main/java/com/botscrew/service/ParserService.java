package com.botscrew.service;

import com.botscrew.domain.Book;
import com.botscrew.domain.Command;
import org.springframework.stereotype.Service;

@Service
public interface ParserService {

    Command parseCommand(String input);

    Book parseBook(String input);

    String parseBookName(String input);
}
