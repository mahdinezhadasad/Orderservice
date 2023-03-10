package com.example.orderservice.repositories;

import com.example.orderservice.ProductStatus;
import com.example.orderservice.domain.Customer;
import com.example.orderservice.domain.OrderApproval;
import com.example.orderservice.domain.OrderHeader;
import com.example.orderservice.domain.OrderLine;
import com.example.orderservice.domain.Product;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {
    
    @Autowired
    OrderHeaderRepository orderHeaderRepository;
    
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderApprovalRepository orderApprovalRepository;
    Product product;
    
    @BeforeEach
    void setUp(){
        Product newProduct = new Product();
        newProduct.setProductStatus (ProductStatus.NEW);
        newProduct.setDescription ("For testing purpose");
        product = productRepository.saveAndFlush (newProduct);
        
    }
    
    @Test
    void testSaveOrder() {
        OrderHeader orderHeader = new OrderHeader();
        Customer customer = new Customer();
        customer.setCustomerName ("New Customer");
        Customer savedCustomer = customerRepository.save(customer);
        
        orderHeader.setCustomer (savedCustomer);
       
        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);
        
        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        
        OrderHeader fetchedOrder = orderHeaderRepository.getById(savedOrder.getId());
        
        assertNotNull(fetchedOrder);
        assertNotNull(fetchedOrder.getId());
    }
    
    @Test
    void testSaveOrderWithLine(){
        
        OrderHeader orderHeader = new OrderHeader();
    
        Customer customer = new Customer();
        customer.setCustomerName ("New Customer");
        Customer savedCustomer = customerRepository.save(customer);
    
        orderHeader.setCustomer (savedCustomer);
    
    
    
    
        OrderLine orderLine = new OrderLine ();
        
        orderLine.setQuantityOrdered (5);
        orderLine.setProduct (product);
        
        //orderHeader.setOrderLines (Set.of (orderLine));
       // orderLine.setOrderHeader (orderHeader);
        
        orderHeader.addOrderLine (orderLine);
    
        OrderApproval approval = new OrderApproval ();
        approval.setApprovedBy ("me");
        OrderApproval savedApproval = orderApprovalRepository.save(approval);
        orderHeader.setOrderApproval (savedApproval);
        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);
        orderHeaderRepository.flush ();
        
        OrderHeader fetchedOrder = orderHeaderRepository.getById (savedOrder.getId ());
        
        assertNotNull (savedOrder);
        assertNotNull (savedOrder.getId ());
        assertNotNull (savedOrder.getOrderLines ());
        assertEquals (savedOrder.getOrderLines ().size() ,1);
    
        assertNotNull(fetchedOrder);
        assertEquals(fetchedOrder.getOrderLines().size(), 1);
    
    
    }
    
    @Test
    void testDeleteCascade(){
        
        OrderHeader orderHeader = new OrderHeader();
        Customer customer = new Customer();
        customer.setCustomerName("new sdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        orderHeader.setCustomer (customerRepository.save (customer));
        
        OrderLine  orderLine = new OrderLine();
        orderLine.setQuantityOrdered (3);
        orderLine.setProduct (product);
        
        OrderApproval orderApproval = new OrderApproval();
        orderApproval.setApprovedBy ("you");
        orderHeader.setOrderApproval (orderApproval);
        
        orderHeader.addOrderLine (orderLine);
        
        OrderHeader savedOrder = orderHeaderRepository.saveAndFlush (orderHeader);
    
        System.out.println ("order saved and flushed");
        
        orderHeaderRepository.deleteById (savedOrder.getId ());
        orderHeaderRepository.flush ();
        assertThrows(EntityNotFoundException.class, () ->{
            
            OrderHeader  fetchedOrder = orderHeaderRepository.getById (savedOrder.getId ());
            assertNull (fetchedOrder);
        });
        
    }
}