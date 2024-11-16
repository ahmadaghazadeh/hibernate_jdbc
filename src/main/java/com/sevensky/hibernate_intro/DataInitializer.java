package com.sevensky.hibernate_intro;

import com.sevensky.hibernate_intro.domain.*;
import com.sevensky.hibernate_intro.repositories.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    public DataInitializer(OrderHeaderRepository orderHeaderRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        OrderHeader o1 = new OrderHeader();
//        o1.setCustomer("Ahmad");
//        o1.setBillToAddress(new Address("a","x","c","d"));
//        o1.setShippingAddress(new Address("a","x","c","d"));
//        o1.setOrderStatus(OrderStatus.New);
//
//        Product product=new Product();
//        product.setName("Apple");
//
//        Product product1=new Product();
//        product1.setName("Apple1");
//
//        Product product2=new Product();
//        product2.setName("Apple2");
//
//        productRepository.saveAll(Set.of(product,product1,product2).stream().toList());
//
//        productRepository.flush();
//
//        Category category=new Category();
//        category.setName("Apples");
//        category.addProduct(product);
//        category.addProduct(product1);
//        category.addProduct(product2);
//
//
//        OrderApproval orderApproval=new OrderApproval();
//        orderApproval.setApproveBy("Ahmad");
//
//
//
//        Category category1= categoryRepository.save(category);
//
//        OrderLine orderLine1 = new OrderLine();
//        orderLine1.setQuantity(1);
//        o1.AddOrderLine(orderLine1);
//        orderLine1.setProduct(product);
//
//        o1.setOrderApproval(orderApproval);
//
//        OrderHeader saved= orderHeaderRepository.save(o1);
//        orderHeaderRepository.flush();
//
//        orderHeaderRepository.deleteById(saved.getId());
//        orderHeaderRepository.flush();

        OrderHeader orderHeader = new OrderHeader();

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(123);
        orderHeader.AddOrderLine(orderLine);

        var orderHeader1=orderHeaderRepository.save(orderHeader);
        System.out.println("Version :1 "+orderHeader1.getVersion());
        orderHeader1.setCustomer("Ahmad");


        var orderHeader2=orderHeaderRepository.save(orderHeader1);
        System.out.println("Version :2 "+orderHeader2.getVersion());

       // var orderHeader3=orderHeaderRepository.save(orderHeader1);
       // System.out.println("Version :3 "+orderHeader3.getVersion());

    }
}
