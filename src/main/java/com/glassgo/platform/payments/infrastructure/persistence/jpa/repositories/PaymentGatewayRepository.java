package com.glassgo.platform.payments.infrastructure.persistence.jpa.repositories;

import com.glassgo.platform.payments.domain.model.aggregates.PaymentGateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for PaymentGateway aggregates.
 */
@Repository
public interface PaymentGatewayRepository extends JpaRepository<PaymentGateway, Long> {

}
