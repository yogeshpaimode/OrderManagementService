package com.eriks.orderservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Order_Details")
public class Order {
    
    @Id
    @SequenceGenerator(name = "ORDER_ID_GENERATOR", sequenceName = "ORDER_IDS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ID_GENERATOR")
    @Column(name = "ORDER_ID", nullable = false)
    private Long id;
    
    @Column(name = "STATUS", length = 15, nullable = false)
    private String status;
    
    @Column(name = "TOTAL_PRICE", precision = 18, scale = 5, nullable = false)
    private BigDecimal totalPrice;
    
    @Column(name = "ORDER_PLACED_TIMESTAMP")
    private LocalDateTime orderDate;

    public Order() {
    }

    public Order(Long id, String status, BigDecimal totalPrice, LocalDateTime orderDate) {
        this.id = id;
        this.status = status;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
