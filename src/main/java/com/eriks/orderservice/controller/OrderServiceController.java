package com.eriks.orderservice.controller;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.eriks.orderservice.model.Order;
import com.eriks.orderservice.service.OrderDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {SwaggerConfiguration.ORDER_SERVICE_TAG})
@RestController
@RequestMapping("/rest/orderservice")
public class OrderServiceController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    // @GetMapping("/orders")
    @ApiOperation(value = "Get Orders List")
    @GetMapping("/getAllOrders")
    public List<Order> getOrdersList() {
        return orderDetailsService.listAll();
    }

    @ApiOperation(value = "Get Orders Details")
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable Integer id) {
        try {
            Order orderDetails = orderDetailsService.get(id);
            return new ResponseEntity<Order>(orderDetails, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Add Order")
    @PostMapping("/orders")
    public ResponseEntity<?> addOrder(@RequestBody Order orderDetails) {
        orderDetailsService.save(orderDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Order Details")
    @PutMapping("/orders/{id}")
    public ResponseEntity<?> updateOrderDetails(@RequestBody Order orderDetails,
            @PathVariable Integer id) {
        try {
            orderDetailsService.get(id);
            orderDetailsService.save(orderDetails);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Delete Order Details")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found")
        }
    )
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer id) {
        try {
            orderDetailsService.get(id);
            orderDetailsService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
