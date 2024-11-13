package com.sevensky.hibernate_intro.dao;

import com.sevensky.hibernate_intro.domain.Author;

public interface AuthorDao {
    Author getById(long id);
    Author getByName(String firstName, String lastName);
    Author save(Author author);
    Author updateAuthor(Author author);

    void deleteAuthor(Author author);
}
