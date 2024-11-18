package com.parquesoftti.tc.service;

import com.parquesoftti.tc.model.CreditCard;
import com.parquesoftti.tc.model.Transaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    @Transactional(readOnly = true)
    List<Transaction> getAllTransactions();

    void saveTransaction(Transaction transaction);

    public Optional<Transaction> getTransactionById(Long id);

    Optional<Transaction> findLastTransactionByCardNumber(String cardNumber);

    void updateTransaction(Transaction existingTransaction);

    void deleteTransactionById(Long id);

}