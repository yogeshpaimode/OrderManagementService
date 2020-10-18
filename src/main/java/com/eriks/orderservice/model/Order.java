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

/**
 * The Class Order.
 * 
 * @author Yogesh Paimode
 */
@Entity
@Table(name = "Order_Details")
public class Order {

    /** The id. */
    @Id
    @SequenceGenerator(name = "ORDER_ID_GENERATOR", sequenceName = "ORDER_IDS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ID_GENERATOR")
    @Column(name = "ORDER_ID", nullable = false)
    private Long id;

    /** The status. */
    @Column(name = "STATUS", length = 15, nullable = false)
    private String status;

    /** The total price. */
    @Column(name = "TOTAL_PRICE", precision = 18, scale = 5, nullable = false)
    private BigDecimal totalPrice;

    /** The order date. */
    @Column(name = "ORDER_PLACED_TIMESTAMP")
    private LocalDateTime orderDate;

    /**
     * Instantiates a new order.
     */
    public Order() {}

    /**
     * Instantiates a new order.
     *
     * @param id the id
     * @param status the status
     * @param totalPrice the total price
     * @param orderDate the order date
     */
    public Order(Long id, String status, BigDecimal totalPrice, LocalDateTime orderDate) {
        this.id = id;
        this.status = status;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the total price.
     *
     * @return the total price
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price.
     *
     * @param totalPrice the new total price
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the order date.
     *
     * @return the order date
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the order date.
     *
     * @param orderDate the new order date
     */
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
