package com.glassgo.platform.paymentsandsubscriptions.infrastructure.persistence.jpa.repositories;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCurrency(String currency);
    List<Transaction> findByPaymentMethod(String paymentMethod);
    List<Transaction> findByStatus(String status);
}
