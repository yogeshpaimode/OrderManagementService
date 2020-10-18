package com.eriks.orderservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

/**
 * The Class OrderDto.
 * 
 * @author Yogesh Paimode
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Order Number")
    private Long id;

    /** The status. */
    @ApiModelProperty(notes = "Order Status")
    private String status;

    /** The total price. */
    @ApiModelProperty(notes = "Order total price in EUR")
    private BigDecimal totalPrice;

    /** The order date. */
    @ApiModelProperty(notes = "Order Placed Date")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime orderDate;

    /**
     * Instantiates a new order dto.
     */
    public OrderDto() {}

    /**
     * Instantiates a new order dto.
     *
     * @param id the id
     * @param status the status
     * @param totalPrice the total price
     * @param orderDate the order date
     */
    public OrderDto(Long id, String status, BigDecimal totalPrice, LocalDateTime orderDate) {
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
