package com.example.orderservice.repositories;

import static org.junit.jupiter.api.Assertions.*;

import com.example.orderservice.ProductStatus;
import com.example.orderservice.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {
    
    
    @Autowired
    ProductRepository productRepository;
    @Test
    void testGetCategory() {
        Product product = productRepository.findByDescription("PRODUCT1").get ();
        
        assertNotNull(product);
        assertNotNull(product.getCategories());
        
    }

    
    @Test
    void testSaveProduct() {
        
        Product product = new Product ();
        product.setDescription ("My Product");
        product.setProductStatus (ProductStatus.NEW);
        
        Product savedProduct = productRepository.save (product);
        
        Product fetched = productRepository.getById (savedProduct.getId ());
        
        assertNotNull (fetched);
        assertNotNull (fetched.getDescription ());
        assertNotNull (fetched.getCreatedDate ());
        assertNotNull (fetched.getLastModifiedDate ());
        
        
    }
    
    @Test
    void addAndUpdateProduct(){
    
    
        Product product = new Product ();
        product.setDescription ("My Product");
        product.setProductStatus (ProductStatus.NEW);
    
        Product savedProduct = productRepository.saveAndFlush (product);
        
        savedProduct.setQuantityOnHand (25);
    
        Product savedProduct2 = productRepository.saveAndFlush (product);
    
    
        System.out.println (savedProduct2.getQuantityOnHand ());
        
        
        
        
        
        
        
    }
    
    
}