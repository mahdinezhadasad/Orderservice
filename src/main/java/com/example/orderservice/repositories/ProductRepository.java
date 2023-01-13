package com.example.orderservice.repositories;

import com.example.orderservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    
    Optional<Product> findByDescription(String description);
}