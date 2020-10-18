package com.eriks.orderservice.respository.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eriks.orderservice.model.Order;

/**
 * The Interface OrderDetailsRepo.
 * 
 * @author Yogesh Paimode
 */
@Repository
public interface OrderDetailsRepo extends JpaRepository<Order, Long> {

    /**
     * Gets the order by id.
     *
     * @param orderId the order id
     * @return the order by id
     */
    Optional<Order> getOrderById(Long orderId);

    /**
     * Save.
     *
     * @param order the order
     * @return the order
     */
    Order save(Order order);
}
