package com.sevensky.hibernate_intro;

import com.sevensky.hibernate_intro.domain.Address;
import com.sevensky.hibernate_intro.domain.OrderHeader;
import com.sevensky.hibernate_intro.domain.OrderStatus;
import com.sevensky.hibernate_intro.repositories.OrderHeaderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local","default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final OrderHeaderRepository orderHeaderRepository;

    public DataInitializer(OrderHeaderRepository orderHeaderRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        OrderHeader o1 = new OrderHeader();
        o1.setCustomer("Ahmad");
        o1.setBillToAddress(new Address("a","x","c","d"));
        o1.setShippingAddress(new Address("a","x","c","d"));
        o1.setOrderStatus(OrderStatus.New);
        orderHeaderRepository.save(o1);
    }
}
