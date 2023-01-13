package com.example.orderservice.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class OrderApproval extends BaseEntity{
    
    private String approvedBy;
    
    public OrderHeader getOrderHeader() {
        return orderHeader;
    }
    
    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }
    @OneToOne
    @JoinColumn(name = "order_header_id")
    private OrderHeader orderHeader;
    
    public String getApprovedBy() {
        return approvedBy;
    }
    
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}