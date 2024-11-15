package com.sevensky.hibernate_intro.dao;

import com.sevensky.hibernate_intro.domain.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> findAll();
    List<Author> listAuthorByLastNameLike(String lastName);
    Author getById(long id);
    Author getByName(String firstName, String lastName);
    Author findAuthorByNameCriteria(String firstName, String lastName);
    Author save(Author author);
    Author updateAuthor(Author author);
    void deleteAuthor(Long id);

    Author findAuthorByNameNative(String firstName, String lastName);
}
