package com.glassgo.platform.payments.infrastructure.persistence.jpa.repositories;

import com.glassgo.platform.payments.domain.model.aggregates.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA repository for Transaction aggregates.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCurrency(String currency);
    List<Transaction> findByPaymentMethod(String paymentMethod);
    List<Transaction> findByStatus(String status);
}
