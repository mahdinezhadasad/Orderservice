package com.example.orderservice.bootsrap;

import com.example.orderservice.domain.Customer;
import com.example.orderservice.domain.OrderHeader;
import com.example.orderservice.repositories.CustomerRepository;
import com.example.orderservice.repositories.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class Bootstrap implements CommandLineRunner {
    
    @Autowired
    BootstrapOrderService bootstrapOrderService;
    
    @Autowired
    OrderHeaderRepository orderHeaderRepository;
    
    @Autowired
    CustomerRepository customerRepository;
    

    
    @Override
    public void run(String... args) throws Exception {
        bootstrapOrderService.readOrderData();
    
        Customer customer = new Customer();
        customer.setCustomerName("Testing Version");
        Customer savedCustomer = customerRepository.save(customer);
        System.out.println("Version is: " + savedCustomer.getVersion());
    
        savedCustomer.setCustomerName("Testing Version 2");
        Customer savedCustomer2 = customerRepository.save(savedCustomer);
        System.out.println("Version is: " + savedCustomer2.getVersion());
    
        savedCustomer2.setCustomerName("Testing Version 3");
        Customer savedCustomer3 = customerRepository.save(savedCustomer2);
        System.out.println("Version is: " + savedCustomer3.getVersion());
    
        customerRepository.delete(savedCustomer3);
       
    }
}