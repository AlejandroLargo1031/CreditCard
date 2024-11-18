package com.parquesoftti.tc.service.impl;

import com.parquesoftti.tc.model.CreditCard;
import com.parquesoftti.tc.model.Transaction;
import com.parquesoftti.tc.repository.TransactionRepository;
import com.parquesoftti.tc.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;


    @Transactional(readOnly = true)
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    @Override
        public Optional<Transaction> getTransactionById(Long id) {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("El ID debe ser positivo y no nulo.");
            }
            return transactionRepository.findById(id);
        }

    @Override
    public Optional<Transaction> findLastTransactionByCardNumber(String cardNumber) {
        return transactionRepository.findTopByCreditCard_CardNumberOrderByDateDesc(cardNumber);
    }

    public void updateTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }


    @Override
    public void deleteTransactionById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser positivo y no nulo.");
        }

        Optional<Transaction> existingTransaction = getTransactionById(id);
        if (existingTransaction.isEmpty()) {
            throw new RuntimeException("La transaccion no existe y no se puede eliminar.");
        }
        transactionRepository.deleteById(id);
    }

}