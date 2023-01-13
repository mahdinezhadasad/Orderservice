package com.example.orderservice.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

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
                column = @Column(name = "bill_to_address")
        ),
        @AttributeOverride(
                name = "billToAddress.city",
                column = @Column(name = "bill_to_city")
        ),
        @AttributeOverride(
                name = "billToAddress.state",
                column = @Column(name = "bill_to_state")
        ),
        @AttributeOverride(
                name = "billToAddress.zipCode",
                column = @Column(name = "bill_to_zip_code")
        )
})
public class OrderHeader extends BaseEntity{
   
    @ManyToOne(fetch = FetchType.LAZY)
  //  @JoinColumn(name = "customer_id")
    private Customer customer;
    @Embedded
    private Address shippingAddress;
    @Embedded
    private Address billToAddress;
    
    
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE} ,mappedBy = "orderHeader")
    private  OrderApproval orderApproval;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany(mappedBy = "orderHeader", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<OrderLine> orderLines ;
    
    public void addOrderLine(OrderLine orderLine){
        
        if(orderLines == null){
            
            orderLines = new HashSet<> ();
        }
        orderLines.add (orderLine);
        orderLine.setOrderHeader (this);
    }
    
    public OrderApproval getOrderApproval() {
        return orderApproval;
    }
    
    public void setOrderApproval(OrderApproval orderApproval) {
        this.orderApproval = orderApproval;
        orderApproval.setOrderHeader (this);
    }
    
    public Address getShippingAddress() {
        return shippingAddress;
    }
    
    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }
    
    public void setOrderLines(Set<OrderLine> orderLines) {
        this.orderLines = orderLines;
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
    
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        if (!super.equals (o)) return false;
        OrderHeader that = (OrderHeader) o;
        return Objects.equals (customer, that.customer) && Objects.equals (shippingAddress, that.shippingAddress) && Objects.equals (billToAddress, that.billToAddress) && orderStatus == that.orderStatus && Objects.equals (orderLines, that.orderLines);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash (super.hashCode (), customer, shippingAddress, billToAddress, orderStatus, orderLines);
    }
}