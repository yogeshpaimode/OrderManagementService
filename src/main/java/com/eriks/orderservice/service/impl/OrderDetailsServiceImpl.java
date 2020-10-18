package com.eriks.orderservice.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eriks.orderservice.dto.OrderDto;
import com.eriks.orderservice.exception.ValidationException;
import com.eriks.orderservice.model.Order;
import com.eriks.orderservice.respository.jpa.OrderDetailsRepo;
import com.eriks.orderservice.service.OrderDetailsService;
import com.eriks.orderservice.util.OrderHelper;

/**
 * The Class OrderDetailsServiceImpl.
 * 
 * @author Yogesh Paimode
 */
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    /** The order details repo. */
    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    /**
     * Gets the orders list.
     *
     * @return the orders list
     */
    @Override
    public List<Order> getOrdersList() {
        return orderDetailsRepo.findAll();
    }

    /**
     * Gets the order details.
     *
     * @param id the id
     * @return the order details
     * @throws NoSuchElementException the no such element exception
     */
    @Override
    public Order getOrderDetails(Long id) throws NoSuchElementException {
        Optional<Order> order = orderDetailsRepo.getOrderById(id);
        return order.isPresent() ? order.get() : null;
    }

    /**
     * Adds the order.
     *
     * @param orderDto the order dto
     * @return the order
     * @throws ValidationException the validation exception
     */
    @Override
    public Order addOrder(OrderDto orderDto) throws ValidationException {
        OrderHelper.validate(orderDto);
        Order order = OrderHelper.mapToOrder(orderDto);
        return orderDetailsRepo.save(order);
    }

    /**
     * Update order details.
     *
     * @param orderId the order id
     * @param orderDto the order dto
     * @return the order
     * @throws ValidationException the validation exception
     */
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

    /**
     * Delete order.
     *
     * @param orderId the order id
     * @throws ValidationException the validation exception
     */
    @Override
    public void deleteOrder(Long orderId) throws ValidationException {
        Optional<Order> order = orderDetailsRepo.getOrderById(orderId);

        if (!order.isPresent()) {
            throw new ValidationException("Invalid Order Id !!");
        }
        orderDetailsRepo.deleteById(orderId);
    }
}
