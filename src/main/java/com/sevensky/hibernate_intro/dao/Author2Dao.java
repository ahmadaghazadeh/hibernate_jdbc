package com.sevensky.hibernate_intro.dao;

import com.sevensky.hibernate_intro.domain.Author;

import java.util.List;

public interface Author2Dao {

    Author getById(long id);
    Author getByName(String firstName, String lastName);
    Author save(Author author);
    Author updateAuthor(Author author);
    void deleteAuthor(Long id);
}
