package com.sevensky.hibernate_intro;
import com.sevensky.hibernate_intro.domain.Book;
import com.sevensky.hibernate_intro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = {"com.sevensky.hibernate_intro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class springBootJpaTestSlice {

    @Autowired
    BookRepository bookRepository;

    @Commit
    @Order(1)
    @Test
    void  testJpaTestSplice(){

        long countBefore=bookRepository.count();

        bookRepository.save(new Book("My book","123","ahmad",null));

        long countAfter=bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }
    @Order(2)
    @Test
    void  testJpaTestSpliceTransaction(){

        long countBefore=bookRepository.count();

        assertThat(countBefore).isEqualTo(3);
    }
}