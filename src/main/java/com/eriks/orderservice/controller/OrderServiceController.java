package com.eriks.orderservice.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eriks.orderservice.config.SwaggerConfiguration;
import com.eriks.orderservice.dto.OrderDto;
import com.eriks.orderservice.exception.ValidationException;
import com.eriks.orderservice.model.Order;
import com.eriks.orderservice.service.OrderDetailsService;
import com.eriks.orderservice.util.OrderHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {SwaggerConfiguration.ORDER_SERVICE_TAG})
@RestController
@RequestMapping("/api/orderservice")
public class OrderServiceController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @ApiOperation(value = "Get Orders List")
    @GetMapping(value = "/getAllOrders", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDto> getOrdersList() {
        return orderDetailsService.getOrdersList().stream().map(OrderHelper :: mapToOrderDto).collect(Collectors.toList());
    }

    @ApiOperation(value = "Get Orders Details")
    @GetMapping(value = "/orders/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> getOrderDetails(@PathVariable Long orderId) {
        try {
            Order orderDetails = orderDetailsService.getOrderDetails(orderId);
            OrderDto orderDto = OrderHelper.mapToOrderDto(orderDetails);
            return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<OrderDto>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Add Order")
    @PostMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addOrder(@RequestBody OrderDto orderDetails) throws ValidationException {
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(OrderHelper.mapToOrderDto(orderDetailsService.addOrder(orderDetails)));
    }

    @ApiOperation(value = "Update Order Details")
    @PutMapping(value = "/orders/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> updateOrderDetails(@RequestBody OrderDto orderDetails,
            @PathVariable Long orderId) throws ValidationException {
        return ResponseEntity.status(HttpStatus.OK)
        .body(OrderHelper.mapToOrderDto(orderDetailsService.updateOrderDetails(orderId, orderDetails)));
    }

    @ApiOperation(value = "Delete Order Details")
    @DeleteMapping(value = "/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) throws ValidationException {
            orderDetailsService.deleteOrder(orderId);
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
