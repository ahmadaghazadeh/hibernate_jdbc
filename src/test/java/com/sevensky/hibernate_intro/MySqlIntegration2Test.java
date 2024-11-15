package com.sevensky.hibernate_intro;

import com.sevensky.hibernate_intro.dao.Author2Dao;
import com.sevensky.hibernate_intro.dao.Author2DaoImpl;
import com.sevensky.hibernate_intro.dao.AuthorDaoImpl;
import com.sevensky.hibernate_intro.domain.Author;
import com.sevensky.hibernate_intro.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
//@ComponentScan(basePackages = {"com.sevensky.hibernate_intro.dao"})
@Import(Author2DaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySqlIntegration2Test {

    @Autowired
    Author2Dao author2Dao;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void testAuthorRepository()  {
        Author author = author2Dao.getById(1L);

        assertThat(author).isNotNull();
    }

    @Test
    void testAuthorByNameRepository()  {
        Author author = author2Dao.getByName("Arad","Aghazadeh");

        System.out.println(author.getId());
        assertThat(author).isNotNull();
    }

    @Test
    void testAuthorSaveRepository()  {
        Author author = new Author("Ahmad","Aghazadeh");

        Author saved= author2Dao.save(author);

        System.out.println(saved.getId());
        assertThat(saved).isNotNull();
    }

    @Test
    void testAuthorUdapteRepository()  {
        Author author = new Author("X1","X1");
        Author saved= author2Dao.save(author);
        saved.setFirstName("Arad");
        Author updateAuthor= author2Dao.updateAuthor(saved);
        System.out.println(updateAuthor.getId());
        assertThat(updateAuthor).isNotNull();
    }

    @Test
    void testAuthorDeleteRepository()  {
        Author author = new Author("Ahmad 2","Aghazadeh 2");
        Author saved= author2Dao.save(author);

        author2Dao.deleteAuthor(saved.getId() );
        assertThrows(EntityNotFoundException.class,()-> author2Dao.getById(saved.getId()));

    }

    @Test
    void testAuthorFindRepository()  {
        Author a=authorRepository.findByFirstName("Arad x");
        assertNull(a);
    }

    @Test
    void testAuthorFindPaging()  {
        List<Author> authors=authorRepository.
                findByFirstNameContaining("Ahmad", PageRequest.of(0,110))
                .getContent();
        assertThat(authors).isNotNull();
    }


}
