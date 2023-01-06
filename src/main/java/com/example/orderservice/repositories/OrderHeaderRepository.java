package com.example.orderservice.repositories;

import com.example.orderservice.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
}