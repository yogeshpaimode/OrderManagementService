package com.eriks.orderservice.service;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import com.eriks.orderservice.dto.OrderDto;
import com.eriks.orderservice.exception.ValidationException;
import com.eriks.orderservice.model.Order;
import com.eriks.orderservice.respository.jpa.OrderDetailsRepo;
import com.eriks.orderservice.service.impl.OrderDetailsServiceImpl;

/**
 * The Class OrderDetailsServiceTest.
 * 
 * @author Yogesh Paimode
 */
@RunWith(SpringRunner.class)
public class OrderDetailsServiceTest {

    /** The order details service impl. */
    @InjectMocks
    private OrderDetailsServiceImpl orderDetailsServiceImpl;

    /** The order details repo. */
    @Mock
    private OrderDetailsRepo orderDetailsRepo;

    /**
     * Given when orders present then fetch all order list.
     */
    @Test
    public void Given_WhenOrdersPresent_ThenFetchAllOrderList() {
        Order order1 = new Order(1L, "In Progress", BigDecimal.valueOf(1000), LocalDateTime.now());
        Order order2 = new Order(2L, "Dispatched", BigDecimal.valueOf(250), LocalDateTime.now());

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
        // When
        Mockito.when(orderDetailsRepo.findAll()).thenReturn(orderList);

        List<Order> expectedOrderList = orderDetailsServiceImpl.getOrdersList();

        assertThat(expectedOrderList, hasSize(2));
        assertEquals(expectedOrderList.get(0).getId(), order1.getId());
    }

    /**
     * Given order I D when order present then get order details.
     */
    @Test
    public void GivenOrderID_WhenOrderPresent_ThenGetOrderDetails() {
        Order order = new Order(1L, "In Progress", BigDecimal.valueOf(1000), LocalDateTime.now());

        // When
        Mockito.when(orderDetailsRepo.getOrderById(order.getId())).thenReturn(Optional.of(order));

        Order expectedOrderDetails = orderDetailsServiceImpl.getOrderDetails(order.getId());

        assertEquals(expectedOrderDetails.getStatus(), order.getStatus());
    }

    /**
     * Given order I D when order not present then get null object.
     */
    @Test
    public void GivenOrderID_WhenOrderNotPresent_ThenGetNullObject() {
        Order order = new Order(1L, "In Progress", BigDecimal.valueOf(1000), LocalDateTime.now());

        // When
        Mockito.when(orderDetailsRepo.getOrderById(order.getId())).thenReturn(Optional.of(order));

        Order expectedOrderDetails = orderDetailsServiceImpl.getOrderDetails(2L);

        assertNull(expectedOrderDetails);
    }

    /**
     * Given when order details provided then add order.
     *
     * @throws ValidationException the validation exception
     */
    @Test
    public void Given_WhenOrderDetailsProvided_ThenAddOrder() throws ValidationException {
        OrderDto orderDto =
                new OrderDto(1L, "In Progress", BigDecimal.valueOf(1000), LocalDateTime.now());
        Order order = new Order(1L, "In Progress", BigDecimal.valueOf(1000), LocalDateTime.now());

        // When
        Mockito.when(orderDetailsRepo.save(any(Order.class))).thenReturn(order);

        Order expectedOrderDetails = orderDetailsServiceImpl.addOrder(orderDto);

        assertEquals(expectedOrderDetails.getId(), order.getId());
    }

    /**
     * Given when order details provided then invalid order status.
     *
     * @throws ValidationException the validation exception
     */
    @Test(expected = ValidationException.class)
    public void Given_WhenOrderDetailsProvided_ThenInvalidOrderStatus() throws ValidationException {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setStatus("");
        orderDto.setOrderDate(LocalDateTime.now());
        orderDto.setTotalPrice(BigDecimal.valueOf(100));

        orderDetailsServiceImpl.addOrder(orderDto);
    }

    /**
     * Given when order details provided then invalid order total price.
     *
     * @throws ValidationException the validation exception
     */
    @Test(expected = ValidationException.class)
    public void Given_WhenOrderDetailsProvided_ThenInvalidOrderTotalPrice()
            throws ValidationException {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setStatus("Dispatched");
        orderDto.setOrderDate(LocalDateTime.now());
        orderDto.setTotalPrice(null);

        orderDetailsServiceImpl.addOrder(orderDto);
    }

    /**
     * Given when order details provided then invalid order date.
     *
     * @throws ValidationException the validation exception
     */
    @Test(expected = ValidationException.class)
    public void Given_WhenOrderDetailsProvided_ThenInvalidOrderDate() throws ValidationException {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setStatus("Dispatched");
        orderDto.setOrderDate(null);
        orderDto.setTotalPrice(BigDecimal.valueOf(1000));

        orderDetailsServiceImpl.addOrder(orderDto);
    }

    /**
     * Given order details for update when order not present then throw validation exception.
     *
     * @throws ValidationException the validation exception
     */
    @Test(expected = ValidationException.class)
    public void GivenOrderDetailsForUpdate_WhenOrderNotPresent_ThenThrowValidationException()
            throws ValidationException {
        OrderDto orderDto =
                new OrderDto(1L, "In Progress", BigDecimal.valueOf(1000), LocalDateTime.now());

        Order order = new Order(1L, "In Progress", BigDecimal.valueOf(1000), LocalDateTime.now());

        // When
        Mockito.when(orderDetailsRepo.getOrderById(order.getId())).thenReturn(Optional.of(order));

        orderDetailsServiceImpl.updateOrderDetails(2L, orderDto);
    }

    /**
     * Given order details for update when order present then update order details.
     *
     * @throws ValidationException the validation exception
     */
    @Test
    public void GivenOrderDetailsForUpdate_WhenOrderPresent_ThenUpdateOrderDetails()
            throws ValidationException {
        OrderDto orderDto =
                new OrderDto(1L, "In Progress", BigDecimal.valueOf(1000), LocalDateTime.now());

        Order order = new Order(1L, "In Progress", BigDecimal.valueOf(1000), LocalDateTime.now());

        // When
        Mockito.when(orderDetailsRepo.getOrderById(order.getId())).thenReturn(Optional.of(order));

        Order order1 = new Order(1L, "Dispatched", BigDecimal.valueOf(1000), LocalDateTime.now());

        // When
        Mockito.when(orderDetailsRepo.save(any(Order.class))).thenReturn(order1);

        Order expectedOrderDetails =
                orderDetailsServiceImpl.updateOrderDetails(orderDto.getId(), orderDto);

        assertEquals(expectedOrderDetails.getStatus(), order1.getStatus());
    }

    /**
     * Given order details for delete when order not present then throw validation exception.
     *
     * @throws ValidationException the validation exception
     */
    @Test(expected = ValidationException.class)
    public void GivenOrderDetailsForDelete_WhenOrderNotPresent_ThenThrowValidationException()
            throws ValidationException {
        Order order = new Order(1L, "In Progress", BigDecimal.valueOf(1000), LocalDateTime.now());
        Long orderId = 2L;

        // When
        Mockito.when(orderDetailsRepo.getOrderById(order.getId())).thenReturn(Optional.of(order));

        orderDetailsServiceImpl.deleteOrder(orderId);
    }

    /**
     * Given order details for delete when order present then delete order.
     *
     * @throws ValidationException the validation exception
     */
    @Test
    public void GivenOrderDetailsForDelete_WhenOrderPresent_ThenDeleteOrder()
            throws ValidationException {
        Order order = new Order(1L, "In Progress", BigDecimal.valueOf(1000), LocalDateTime.now());
        Long orderId = 1L;

        // When
        Mockito.when(orderDetailsRepo.getOrderById(order.getId())).thenReturn(Optional.of(order));

        orderDetailsServiceImpl.deleteOrder(orderId);

        verify(orderDetailsRepo, times(1)).deleteById(orderId);
    }

}
