package com.eriks.orderservice.service;

import java.util.List;
import com.eriks.orderservice.model.Order;

public interface OrderDetailsService {

    public List<Order> listAll();
    
    public void save(Order orderDetails);

    public Order get(Integer id);
    
    public void delete(Integer id);
}
