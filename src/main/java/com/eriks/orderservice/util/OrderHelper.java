package com.eriks.orderservice.util;

import com.eriks.orderservice.dto.OrderDto;
import com.eriks.orderservice.exception.ValidationException;
import com.eriks.orderservice.model.Order;

/**
 * The Class OrderHelper.
 * 
 * @author Yogesh Paimode
 */
public class OrderHelper {

    /**
     * Validate.
     *
     * @param orderDto the order dto
     * @throws ValidationException the validation exception
     */
    public static void validate(OrderDto orderDto) throws ValidationException {
        boolean isValid = true;

        if (orderDto == null) {
            isValid = false;
        } else if (orderDto.getStatus() == null || orderDto.getStatus().trim().isEmpty()) {
            isValid = false;
        } else if (orderDto.getTotalPrice() == null) {
            isValid = false;
        } else if (orderDto.getOrderDate() == null) {
            isValid = false;
        }

        if (!isValid) {
            throw new ValidationException("Kindly verify provided attributes !!");
        }
    }

    /**
     * Map to order.
     *
     * @param orderDto the order dto
     * @return the order
     */
    public static Order mapToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setStatus(orderDto.getStatus());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setOrderDate(orderDto.getOrderDate());
        return order;
    }

    /**
     * Map to order dto.
     *
     * @param order the order
     * @return the order dto
     */
    public static OrderDto mapToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setStatus(order.getStatus());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setOrderDate(order.getOrderDate());
        return orderDto;
    }

}
