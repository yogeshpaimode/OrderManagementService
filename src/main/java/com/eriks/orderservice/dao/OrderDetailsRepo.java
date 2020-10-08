package com.eriks.orderservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eriks.orderservice.model.Order;

@Repository
public interface OrderDetailsRepo extends JpaRepository<Order, Integer> {

}
