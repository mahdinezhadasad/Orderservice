package com.example.orderservice.repositories;

import com.example.orderservice.domain.Customer;
import com.example.orderservice.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
    Collection<OrderHeader> findAllByCustomer(Customer customer);
}