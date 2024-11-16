package com.sevensky.hibernate_intro.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.OptimisticLock;

import java.util.Objects;

@Entity
public class OrderLine extends BaseEntity {


    private Integer quantity;



    @ManyToOne
    private OrderHeader orderHeader;


    @ManyToOne
    private Product product;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader order) {
        this.orderHeader = order;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderLine orderLine)) return false;
        if (!super.equals(o)) return false;

        return Objects.equals(getQuantity(), orderLine.getQuantity()) && Objects.equals(getProduct(), orderLine.getProduct());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(getQuantity());
        result = 31 * result + Objects.hashCode(getProduct());
        return result;
    }


}
