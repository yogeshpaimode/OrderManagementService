package com.eriks.orderservice.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eriks.orderservice.dao.OrderDetailsRepo;
import com.eriks.orderservice.model.Order;
import com.eriks.orderservice.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{
    
    @Autowired
    private OrderDetailsRepo orderDetailsRepo;
    
    public List<Order> listAll() {
        return orderDetailsRepo.findAll();
    }
    
    public void save(Order orderDetails) {
        orderDetailsRepo.save(orderDetails);
    }

    public Order get(Integer id) {
        return orderDetailsRepo.findById(id).get();
    }
    
    public void delete(Integer id) {
        orderDetailsRepo.deleteById(id);
    }
}
