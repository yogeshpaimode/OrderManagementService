package com.eriks.orderservice.respository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eriks.orderservice.model.Order;
import java.util.Optional;

@Repository
public interface OrderDetailsRepo extends JpaRepository<Order, Long> {

    Optional<Order> getOrderById(Long orderId);
    
    Order save(Order order);
}
