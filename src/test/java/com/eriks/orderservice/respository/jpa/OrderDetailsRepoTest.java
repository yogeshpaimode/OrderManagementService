package com.eriks.orderservice.respository.jpa;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import com.eriks.orderservice.model.Order;


/**
 * The Class OrderDetailsRepoTest.
 * 
 * @author Yogesh Paimode
 */
@DataJpaTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class OrderDetailsRepoTest {

    /** The order details repo. */
    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    /**
     * Test A given order object when order provided then save order.
     */
    @Test
    @Rollback(false)
    public void TestA_GivenOrderObject_WhenOrderProvided_ThenSaveOrder() {
        Order orderDetails = new Order();
        orderDetails.setStatus("In Progress");
        orderDetails.setTotalPrice(BigDecimal.valueOf(100));
        orderDetails.setOrderDate(LocalDateTime.now());

        assertNotNull(orderDetailsRepo.save(orderDetails));
    }

    /**
     * Test B given when fetch all orders then list all orders.
     */
    @Test
    @Rollback(false)
    public void TestB_Given_WhenFetchAllOrders_ThenListAllOrders() {
        List<Order> orderDetailsList = orderDetailsRepo.findAll();

        assertThat(orderDetailsList, hasSize(5));

    }

    /**
     * Test C given order id when order present then fetch order.
     */
    @Test
    @Rollback(false)
    public void TestC_GivenOrderId_WhenOrderPresent_ThenFetchOrder() {
        Long orderId = 5L;
        Order orderDetails = orderDetailsRepo.getOrderById(orderId).get();

        assertEquals(orderDetails.getId(), orderId);
    }

    /**
     * Test D given order id when order not present then fetch null object.
     */
    @Test
    @Rollback(false)
    public void TestD_GivenOrderId_WhenOrderNotPresent_ThenFetchNullObject() {
        Long orderId = 6L;
        Optional<Order> orderDetails = orderDetailsRepo.getOrderById(orderId);

        assertFalse(orderDetails.isPresent());
    }

    /**
     * Test E given order details when order present then update order details.
     */
    @Test
    @Rollback(false)
    public void TestE_GivenOrderDetails_WhenOrderPresent_ThenUpdateOrderDetails() {
        Long orderId = 3L;
        String orderStatus = "Delivered";
        Optional<Order> order = orderDetailsRepo.getOrderById(orderId);

        if (order.isPresent()) {
            Order orderDetails = order.get();
            orderDetails.setStatus(orderStatus);
            orderDetailsRepo.save(orderDetails);

            assertEquals(orderStatus, orderDetails.getStatus());
        }
    }

    /**
     * Test F given order I D when order present then delete order.
     */
    @Test
    @Rollback(false)
    public void TestF_GivenOrderID_WhenOrderPresent_ThenDeleteOrder() {
        Long orderId = 5L;

        boolean isExistBeforeDelete = orderDetailsRepo.getOrderById(orderId).isPresent();

        orderDetailsRepo.deleteById(orderId);

        boolean isExistAfterDelete = orderDetailsRepo.getOrderById(orderId).isPresent();

        assertTrue(isExistBeforeDelete);
        assertFalse(isExistAfterDelete);
    }

}
