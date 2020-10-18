package com.eriks.orderservice.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.eriks.orderservice.dto.OrderDto;


/**
 * The Class OrderServiceControllerTest.
 * 
 * @author Yogesh Paimode
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OrderServiceController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class OrderServiceControllerTest {

    /** The mock mvc. */
    @Autowired
    private MockMvc mockMvc;

    /** The order service controller. */
    @MockBean
    private OrderServiceController orderServiceController;

    /**
     * Test get orders list.
     *
     * @throws Exception the exception
     */
    @Test
    public void Given_WhenOrderListExist_ThenFetchOrdersList() throws Exception {

        List<OrderDto> orderList = new ArrayList<>();
        OrderDto order1 = new OrderDto();
        order1.setId(1L);
        order1.setStatus("In Progress");
        order1.setTotalPrice(BigDecimal.valueOf(100));
        order1.setOrderDate(LocalDateTime.now());

        OrderDto order2 = new OrderDto();
        order2.setId(2L);
        order2.setStatus("Order Placed");
        order2.setTotalPrice(BigDecimal.valueOf(200));
        order2.setOrderDate(LocalDateTime.now());

        OrderDto order3 = new OrderDto();
        order3.setId(3L);
        order3.setStatus("Order DIspatched");
        order3.setTotalPrice(BigDecimal.valueOf(300));
        order3.setOrderDate(LocalDateTime.now());

        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);

        given(orderServiceController.getOrdersList()).willReturn(orderList);

        mockMvc.perform(get("/api/orderservice/getAllOrders").with(user("eriks").password("secret"))
                .contentType(APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].status", is(order1.getStatus())));
    }

    /**
     * Test get order details.
     *
     * @throws Exception the exception
     */
    @Test
    public void GivenOrderId_WhenOrderExist_ThenFetchOrderDetails() throws Exception {

        OrderDto order = new OrderDto();
        order.setId(1L);
        order.setStatus("In Progress");
        order.setTotalPrice(BigDecimal.valueOf(100));
        order.setOrderDate(LocalDateTime.now());

        given(orderServiceController.getOrderDetails(order.getId()))
                .willReturn(new ResponseEntity<OrderDto>(order, HttpStatus.OK));

        mockMvc.perform(get("/api/orderservice/orders/" + order.getId())
                .with(user("eriks").password("secret")).contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status", is(order.getStatus())));
    }
}
