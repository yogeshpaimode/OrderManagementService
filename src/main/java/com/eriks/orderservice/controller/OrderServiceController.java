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
import org.springframework.web.bind.annotation.RestController;
import com.eriks.orderservice.model.Order;
import com.eriks.orderservice.service.OrderDetailsService;

@RestController
public class OrderServiceController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    //@GetMapping("/orders")
    @GetMapping("/getAllOrders")
    public List<Order> list() {
        return orderDetailsService.listAll();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> get(@PathVariable Integer id) {
        try {
            Order orderDetails = orderDetailsService.get(id);
            return new ResponseEntity<Order>(orderDetails, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/orders")
    public ResponseEntity<?> add(@RequestBody Order orderDetails) {
        orderDetailsService.save(orderDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PutMapping("/orders/{id}")
    public ResponseEntity<?> update(@RequestBody Order orderDetails, @PathVariable Integer id) {
        try {
            orderDetailsService.get(id);
            orderDetailsService.save(orderDetails);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            orderDetailsService.get(id);
            orderDetailsService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
