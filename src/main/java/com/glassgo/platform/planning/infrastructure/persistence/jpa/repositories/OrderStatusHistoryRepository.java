package com.glassgo.platform.planning.infrastructure.persistence.jpa.repositories;

import com.glassgo.platform.planning.domain.model.entities.OrderStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory, Long> {
}
