package com.eriks.orderservice.service;

import java.util.List;
import java.util.NoSuchElementException;
import com.eriks.orderservice.dto.OrderDto;
import com.eriks.orderservice.exception.ValidationException;
import com.eriks.orderservice.model.Order;

/**
 * The Interface OrderDetailsService.
 * 
 * @author Yogesh Paimode
 */
public interface OrderDetailsService {

    /**
     * Gets the orders list.
     *
     * @return the orders list
     */
    public List<Order> getOrdersList();

    /**
     * Adds the order.
     *
     * @param orderDto the order dto
     * @return the order
     * @throws ValidationException the validation exception
     */
    public Order addOrder(OrderDto orderDto) throws ValidationException;

    /**
     * Gets the order details.
     *
     * @param id the id
     * @return the order details
     * @throws NoSuchElementException the no such element exception
     */
    public Order getOrderDetails(Long id) throws NoSuchElementException;

    /**
     * Delete order.
     *
     * @param id the id
     * @throws ValidationException the validation exception
     */
    public void deleteOrder(Long id) throws ValidationException;

    /**
     * Update order details.
     *
     * @param orderId the order id
     * @param orderDto the order dto
     * @return the order
     * @throws ValidationException the validation exception
     */
    public Order updateOrderDetails(Long orderId, OrderDto orderDto) throws ValidationException;
}
