package com.sevensky.hibernate_intro;

import com.sevensky.hibernate_intro.domain.*;
import com.sevensky.hibernate_intro.repositories.OrderHeaderRepository;
import com.sevensky.hibernate_intro.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Set;

@Profile({"local","default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final OrderHeaderRepository orderHeaderRepository;
    private final ProductRepository productRepository;

    public DataInitializer(OrderHeaderRepository orderHeaderRepository, ProductRepository productRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        OrderHeader o1 = new OrderHeader();
        o1.setCustomer("Ahmad");
        o1.setBillToAddress(new Address("a","x","c","d"));
        o1.setShippingAddress(new Address("a","x","c","d"));
        o1.setOrderStatus(OrderStatus.New);

        Product product=new Product();
        product.setName("Apple");

        productRepository.save(product);
        productRepository.flush();

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setQuantity(1);
        o1.setOrderLines(Set.of(orderLine1));
        orderLine1.setProduct(product);

        OrderHeader saved= orderHeaderRepository.save(o1);

    }
}
