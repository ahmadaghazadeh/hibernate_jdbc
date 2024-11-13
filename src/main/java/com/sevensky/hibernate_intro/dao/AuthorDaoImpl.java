package com.sevensky.hibernate_intro.dao;

import com.sevensky.hibernate_intro.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getById(long id) {
        String sql = "SELECT a.id,a.first_name,a.last_name,b.id book_id,b.isbn,b.publisher, b.title FROM author a left OUTER JOIN book b on a.id=b.author_id where a.id = ?";
        return jdbcTemplate.query(sql,new AuthorExtractor(),id);
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        return jdbcTemplate.queryForObject("SELECT * FROM author where first_name = ? and last_name= ? ",
                getRowMapper(),
                firstName,
                lastName);
    }

    @Override
    public Author save(Author author) {
        jdbcTemplate.update("INSERT INTO author(first_name, last_name) VALUES(?,?)",
                author.getFirstName(),
                author.getLastName());
        Long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",Long.class);
        return this.getById(createdId);
    }

    @Override
    public Author updateAuthor(Author author) {
        jdbcTemplate.update("Update author set first_name = ?, last_name = ? where id = ?",
                author.getFirstName(),
                author.getLastName(),
                author.getId());

        return this.getById(author.getId());
    }

    @Override
    public void deleteAuthor(Author author) {
        jdbcTemplate.update("DELETE FROM author where id = ?",author.getId());
    }

    private RowMapper<Author> getRowMapper(){
        return new AuthorMapper();
    }
}
