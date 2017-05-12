package com.botscrew.repository;

import com.botscrew.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BookRepository extends CrudRepository <Book, Integer> {

    Collection<Book> findByName(String name);
}
