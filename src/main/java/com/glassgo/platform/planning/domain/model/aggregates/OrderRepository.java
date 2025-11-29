package com.glassgo.platform.planning.domain.model.aggregates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderNumber(String orderNumber);

    List<Order> findByCustomerName(String customerName);

    List<Order> findByCustomerEmail(String customerEmail);

    List<Order> findByStatus(Order.OrderStatus status);

    @Query("SELECT o FROM Order o WHERE o.deliveryInfo.deliveryDate.value = :deliveryDate")
    List<Order> findByDeliveryDate(@Param("deliveryDate") LocalDate deliveryDate);

    @Query("SELECT o FROM Order o WHERE o.customerName LIKE %:customerName% OR o.customerEmail LIKE %:customerEmail%")
    List<Order> findByCustomerNameOrEmail(@Param("customerName") String customerName,
                                         @Param("customerEmail") String customerEmail);

    @Query("SELECT o FROM Order o WHERE o.status IN :statuses ORDER BY o.createdAt DESC")
    List<Order> findByStatusIn(@Param("statuses") List<Order.OrderStatus> statuses);

    boolean existsByOrderNumber(String orderNumber);
}
