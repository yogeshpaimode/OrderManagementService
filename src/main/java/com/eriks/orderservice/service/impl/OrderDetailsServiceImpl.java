package com.eriks.orderservice.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eriks.orderservice.dto.OrderDto;
import com.eriks.orderservice.exception.ValidationException;
import com.eriks.orderservice.model.Order;
import com.eriks.orderservice.respository.jpa.OrderDetailsRepo;
import com.eriks.orderservice.service.OrderDetailsService;
import com.eriks.orderservice.util.OrderHelper;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Override
    public List<Order> getOrdersList() {
        return orderDetailsRepo.findAll();
    }

    @Override
    public Order getOrderDetails(Long id) {
        return orderDetailsRepo.getOrderById(id).get();
        //return order.isPresent() ? order.get() : null;
    }

    @Override
    public Order addOrder(OrderDto orderDto) throws ValidationException {
        OrderHelper.validate(orderDto);
        Order order = OrderHelper.mapToOrder(orderDto);
        return orderDetailsRepo.save(order);
    }

    @Override
    public Order updateOrderDetails(Long orderId, OrderDto orderDto) throws ValidationException {
        OrderHelper.validate(orderDto);
        Optional<Order> order = orderDetailsRepo.getOrderById(orderId);

        if (!order.isPresent()) {
            throw new ValidationException("Invalid Order Id !!");
        }

        Order orderDetails = order.get();
        orderDetails.setStatus(orderDto.getStatus());
        orderDetails.setTotalPrice(orderDto.getTotalPrice());
        orderDetails.setOrderDate(orderDto.getOrderDate());
        return orderDetailsRepo.save(orderDetails);
    }

    @Override
    public void deleteOrder(Long orderId) throws ValidationException {
        Optional<Order> order = orderDetailsRepo.getOrderById(orderId);

        if (!order.isPresent()) {
            throw new ValidationException("Invalid Order Id !!");
        }
        orderDetailsRepo.deleteById(orderId);
    }


}
