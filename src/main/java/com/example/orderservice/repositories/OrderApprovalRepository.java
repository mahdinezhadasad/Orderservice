package com.example.orderservice.repositories;

import com.example.orderservice.domain.OrderApproval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderApprovalRepository extends JpaRepository<OrderApproval,Long> {
}