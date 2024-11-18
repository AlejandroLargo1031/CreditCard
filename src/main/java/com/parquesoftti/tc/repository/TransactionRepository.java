package com.parquesoftti.tc.repository;

import com.parquesoftti.tc.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findTopByCreditCard_CardNumberOrderByDateDesc(String cardNumber);

}