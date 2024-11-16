package com.sevensky.hibernate_intro.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AttributeOverrides({
        @AttributeOverride(
                name = "shippingAddress.address",
                column = @Column(name = "shipping_address")
        ),
        @AttributeOverride(
                name = "shippingAddress.city",
                column = @Column(name = "shipping_city")
        ),
        @AttributeOverride(
                name = "shippingAddress.state",
                column = @Column(name = "shipping_state")
        ),
        @AttributeOverride(
                name = "shippingAddress.zipCode",
                column = @Column(name = "shipping_zip_code")
        ),
        @AttributeOverride(
                name = "billToAddress.address",
                column = @Column(name = "billToAddress_address")
        ),
        @AttributeOverride(
                name = "billToAddress.city",
                column = @Column(name = "billToAddress_city")
        ),
        @AttributeOverride(
                name = "billToAddress.state",
                column = @Column(name = "billToAddress_state")
        ),
        @AttributeOverride(
                name = "billToAddress.zipCode",
                column = @Column(name = "billToAddress_zip_code")
        ),
})
public class OrderHeader extends BaseEntity{

    private String customer;

    @Embedded
    private Address shippingAddress;

    @Embedded
    private Address billToAddress;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orderHeader", cascade = {CascadeType.PERSIST, CascadeType.REMOVE},fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Set<OrderLine> orderLines= new HashSet<>();

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, mappedBy = "orderHeader")
    private OrderApproval orderApproval;

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public void setOrderApproval(OrderApproval orderApproval) {
        this.orderApproval = orderApproval;
        orderApproval.setOrderHeader(this);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillToAddress() {
        return billToAddress;
    }

    public void setBillToAddress(Address billToAddress) {
        this.billToAddress = billToAddress;
    }



    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

   public void AddOrderLine(OrderLine orderLine) {
       orderLines.add(orderLine);
       orderLine.setOrderHeader(this);
   }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderHeader that)) return false;
        if (!super.equals(o)) return false;

        return Objects.equals(getCustomer(), that.getCustomer()) && Objects.equals(getShippingAddress(), that.getShippingAddress()) && Objects.equals(getBillToAddress(), that.getBillToAddress()) && getOrderStatus() == that.getOrderStatus() && Objects.equals(getOrderLines(), that.getOrderLines());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(getCustomer());
        result = 31 * result + Objects.hashCode(getShippingAddress());
        result = 31 * result + Objects.hashCode(getBillToAddress());
        result = 31 * result + Objects.hashCode(getOrderStatus());
        result = 31 * result + Objects.hashCode(getOrderLines());
        return result;
    }
}
