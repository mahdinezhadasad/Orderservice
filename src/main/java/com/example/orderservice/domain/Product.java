package com.example.orderservice.domain;

import com.example.orderservice.ProductStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

@Entity
public class Product extends BaseEntity{
    
    private String description;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public ProductStatus getProductStatus() {
        return productStatus;
    }
    
    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        if (!super.equals (o)) return false;
        Product product = (Product) o;
        return Objects.equals (description, product.description) && productStatus == product.productStatus;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash (super.hashCode (), description, productStatus);
    }
}