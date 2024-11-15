package com.sevensky.hibernate_intro;

import com.sevensky.hibernate_intro.dao.Author2DaoImpl;
import com.sevensky.hibernate_intro.domain.Address;
import com.sevensky.hibernate_intro.domain.Book;
import com.sevensky.hibernate_intro.domain.OrderHeader;
import com.sevensky.hibernate_intro.domain.OrderStatus;
import com.sevensky.hibernate_intro.repositories.BookRepository;
import com.sevensky.hibernate_intro.repositories.OrderHeaderRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("local")
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    void testEquals() {

        OrderHeader o1 = new OrderHeader();
        o1.setId(1L);

        OrderHeader o2 = new OrderHeader();
        o2.setId(1L);

        assert o1.equals(o2);
    }

    @Test
    void testNotEquals() {

        OrderHeader o1 = new OrderHeader();
        o1.setId(2L);

        OrderHeader o2 = new OrderHeader();
        o2.setId(1L);

        assertNotEquals(o1, o2);
    }


    @Test
    void testAddOrderHeader() {

        OrderHeader o1 = new OrderHeader();
        o1.setCustomer("Ahmad");
        o1.setBillToAddress(new Address("a","x","c","d"));
        o1.setShippingAddress(new Address("a","x","c","d"));
        o1.setOrderStatus(OrderStatus.New);

        var tt= orderHeaderRepository.save(o1);
        assertNotNull(tt);

    }

    @Test
    void testGetOrderHeader() {

        var tt= orderHeaderRepository.getReferenceById(1L);
        assertNotNull(tt);

    }

    @Test
    void testBook() {
        Book b = new Book("x","x","x",null);
        bookRepository.save(b);
    }

}