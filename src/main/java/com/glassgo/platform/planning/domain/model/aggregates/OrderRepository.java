package com.glassgo.platform.planning.domain.model.aggregates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for {@link Order} aggregates.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Finds an order by its order number.
     *
     * @param orderNumber the order number
     * @return an Optional containing the order if found
     */
    Optional<Order> findByOrderNumber(String orderNumber);

    /**
     * Finds all orders by customer name.
     *
     * @param customerName the customer name
     * @return a list of orders
     */
    List<Order> findByCustomerName(String customerName);

    /**
     * Finds all orders by customer email.
     *
     * @param customerEmail the customer email
     * @return a list of orders
     */
    List<Order> findByCustomerEmail(String customerEmail);

    /**
     * Finds all orders by status.
     *
     * @param status the order status
     * @return a list of orders
     */
    List<Order> findByStatus(Order.OrderStatus status);

    /**
     * Finds all orders by delivery date.
     *
     * @param deliveryDate the delivery date
     * @return a list of orders
     */
    @Query("SELECT o FROM Order o WHERE o.deliveryInfo.deliveryDate = :deliveryDate")
    List<Order> findByDeliveryDate(@Param("deliveryDate") LocalDate deliveryDate);

    /**
     * Finds orders by customer name or email containing the search terms.
     *
     * @param customerName the customer name search term
     * @param customerEmail the customer email search term
     * @return a list of orders
     */
    @Query("SELECT o FROM Order o WHERE o.customerName LIKE %:customerName% OR o.customerEmail LIKE %:customerEmail%")
    List<Order> findByCustomerNameOrEmail(@Param("customerName") String customerName,
                                         @Param("customerEmail") String customerEmail);

    /**
     * Finds orders by a list of statuses, ordered by creation date descending.
     *
     * @param statuses the list of order statuses
     * @return a list of orders
     */
    @Query("SELECT o FROM Order o WHERE o.status IN :statuses ORDER BY o.createdAt DESC")
    List<Order> findByStatusIn(@Param("statuses") List<Order.OrderStatus> statuses);

    /**
     * Checks if an order exists with the given order number.
     *
     * @param orderNumber the order number
     * @return true if exists, false otherwise
     */
    boolean existsByOrderNumber(String orderNumber);
}
