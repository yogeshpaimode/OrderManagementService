package com.eriks.orderservice.util;

import com.eriks.orderservice.dto.OrderDto;
import com.eriks.orderservice.exception.ValidationException;
import com.eriks.orderservice.model.Order;

public class OrderHelper {
    
    public static void validate(OrderDto orderDto) throws ValidationException {
        boolean isValid = true;
        
        if(orderDto == null) {
            isValid = false;
        } else if(orderDto.getStatus() == null || orderDto.getStatus().trim().isEmpty()) {
            isValid = false;
        } else if(orderDto.getTotalPrice() == null) {
            isValid = false;
        } else if(orderDto.getOrderDate() == null) {
            isValid = false;
        }
        
        if(!isValid) {
            throw new ValidationException("Kindly verify provided attributes !!");
        }
    }
    
    public static Order mapToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setStatus(orderDto.getStatus());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setOrderDate(orderDto.getOrderDate());
        return order;
    }
    
    public static OrderDto mapToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setStatus(order.getStatus());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setOrderDate(order.getOrderDate());
        return orderDto;
    }

}
