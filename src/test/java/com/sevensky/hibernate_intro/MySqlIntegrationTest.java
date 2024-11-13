package com.sevensky.hibernate_intro;

import com.sevensky.hibernate_intro.dao.AuthorDao;
import com.sevensky.hibernate_intro.dao.AuthorDaoImpl;
import com.sevensky.hibernate_intro.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
//@ComponentScan(basePackages = {"com.sevensky.hibernate_intro.dao"})
@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySqlIntegrationTest {


    @Autowired
    AuthorDao authorDao;


    @Test
    void testAuthorRepository()  {
        Author author = authorDao.getById(1L);

        assertThat(author).isNotNull();
    }

    @Test
    void testAuthorByNameRepository()  {
        Author author = authorDao.getByName("Arad","Aghazadeh");

        System.out.println(author.getId());
        assertThat(author).isNotNull();
    }

    @Test
    void testAuthorSaveRepository()  {
        Author author = new Author("Ahmad","Aghazadeh");

       Author saved= authorDao.save(author);

        System.out.println(saved.getId());
        assertThat(saved).isNotNull();
    }

    @Test
    void testAuthorUdapteRepository()  {
        Author author = new Author("X1","X1");
        Author saved= authorDao.save(author);

        saved.setFirstName("Arad");
        Author updateAuthor= authorDao.updateAuthor(saved);
        System.out.println(updateAuthor.getId());
        assertThat(updateAuthor).isNotNull();
    }

    @Test
    void testAuthorDeleteRepository()  {
        Author author = new Author("Ahmad 1","Aghazadeh 1");
        Author saved= authorDao.save(author);

        authorDao.deleteAuthor(saved);
        assertThrows(EmptyResultDataAccessException.class,()->authorDao.getById(saved.getId()));

    }

}
