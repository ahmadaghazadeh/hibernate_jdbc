package com.sevensky.hibernate_intro.dao;

import com.sevensky.hibernate_intro.domain.Author;
import com.sevensky.hibernate_intro.repositories.AuthorRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;

public class Author2DaoImpl implements Author2Dao {

    private final AuthorRepository authorRepository;

    public Author2DaoImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getById(long id) {
        return authorRepository.getReferenceById(id);
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(EntityExistsException::new);
    }

    @Override
    public Author save(Author author) {
      return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author updateAuthor(Author author) {
        Author oldAuthor = getById(author.getId());
        oldAuthor.setFirstName(author.getFirstName());
        oldAuthor.setLastName(author.getLastName());
        return authorRepository.save(oldAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
