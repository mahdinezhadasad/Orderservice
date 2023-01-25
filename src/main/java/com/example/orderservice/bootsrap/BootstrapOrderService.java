package com.example.orderservice.bootsrap;

import com.example.orderservice.domain.OrderHeader;
import com.example.orderservice.repositories.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BootstrapOrderService {
    
    @Autowired
    OrderHeaderRepository orderHeaderRepository;
    
    
    
    @Transactional
    public void readOrderData() {
        OrderHeader orderHeader = orderHeaderRepository.findById (1L).get ();
        
        orderHeader.getOrderLines ().forEach (ol -> {
            System.out.println (ol.getProduct ().getDescription ());
            
            ol.getProduct ().getCategories ().forEach (cat ->{
                System.out.println (cat.getDescription ());
            });
        });
    }
}