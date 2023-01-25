package com.example.orderservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;

import java.util.Objects;

@Entity
public class OrderLine extends BaseEntity{
    
    private Integer quantityOrdered ;
    @ManyToOne
    private OrderHeader orderHeader;
    @ManyToOne
    private Product product;
    
    @Version
    private Integer version;
    
    public Integer getVersion() {
        return version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }
    
    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }
    
    public OrderHeader getOrderHeader() {
        return orderHeader;
    }
    
    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        if (!super.equals (o)) return false;
        OrderLine orderLine = (OrderLine) o;
        return Objects.equals (quantityOrdered, orderLine.quantityOrdered) && Objects.equals (orderHeader, orderLine.orderHeader) && Objects.equals (product, orderLine.product);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash (super.hashCode (), quantityOrdered, product);
    }
}