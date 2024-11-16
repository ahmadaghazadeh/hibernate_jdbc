package com.sevensky.hibernate_intro;

import com.sevensky.hibernate_intro.dao.Author2DaoImpl;
import com.sevensky.hibernate_intro.domain.*;
import com.sevensky.hibernate_intro.repositories.BookRepository;
import com.sevensky.hibernate_intro.repositories.OrderHeaderRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
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


    @Disabled
    @Rollback(value = false)
    @Test
    void testAddOrderHeader() {

        OrderHeader o1 = new OrderHeader();
        o1.setCustomer("Ahmad");
        o1.setBillToAddress(new Address("a","x","c","d"));
        o1.setShippingAddress(new Address("a","x","c","d"));
        o1.setOrderStatus(OrderStatus.New);
        OrderLine orderLine1 = new OrderLine();
        orderLine1.setQuantity(1);

        OrderLine orderLine2 = new OrderLine();
        orderLine2.setQuantity(2);

        o1.AddOrderLine(orderLine1);
        o1.AddOrderLine(orderLine2);

        var tt= orderHeaderRepository.save(o1);

        System.out.println("Before Test");
        OrderHeader orderHeader= orderHeaderRepository.getById(tt.getId());
        System.out.println("Order Header"+orderHeader.getId());
        System.out.println("Nothing to do");
        System.out.println("Order line size: "+orderHeader.getOrderLines().size());
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