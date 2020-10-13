package com.eriks.orderservice.service;

import java.util.List;
import com.eriks.orderservice.dto.OrderDto;
import com.eriks.orderservice.exception.ValidationException;
import com.eriks.orderservice.model.Order;

public interface OrderDetailsService {

    public List<Order> getOrdersList();
    
    public Order addOrder(OrderDto orderDto) throws ValidationException;

    public Order getOrderDetails(Long id);
    
    public void deleteOrder(Long id) throws ValidationException;
    
    public Order updateOrderDetails(Long orderId, OrderDto orderDto) throws ValidationException;
}
