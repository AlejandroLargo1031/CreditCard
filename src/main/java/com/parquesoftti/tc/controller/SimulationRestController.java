package com.parquesoftti.tc.controller;

import com.parquesoftti.tc.model.CreditCard;
import com.parquesoftti.tc.model.Transaction;
import com.parquesoftti.tc.service.CreditCardService;
import com.parquesoftti.tc.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@RestController
@RequestMapping("/api/v1/sessions")
public class SimulationRestController {

    private final TransactionService transactionService;
    private final CreditCardService creditCardService;

    @GetMapping()
    public ResponseEntity<List<Transaction>> getTran(){
        return ResponseEntity.ok().body(transactionService.getAllTransactions());
    }

    @PostMapping("/pay")
    public ResponseEntity<String> processPayment(@RequestParam String cardNumber, @RequestParam BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment rejected: invalid amount.");
        }

        Transaction existingTransaction = transactionService.findLastTransactionByCardNumber(cardNumber)
                .orElse(null);

        if (existingTransaction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment rejected: no previous transaction found.");
        }

        if (existingTransaction.getAmount().compareTo(amount) < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment rejected: insufficient balance.");
        }

        BigDecimal newAmount = existingTransaction.getAmount().subtract(amount);
        existingTransaction.setAmount(newAmount);
        transactionService.updateTransaction(existingTransaction);

        return ResponseEntity.ok("Payment successful. Transaction updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> rejectPayment( @PathVariable Long id) {
        transactionService.deleteTransactionById(id);
        return ResponseEntity.ok().body("Transaction deleted");
    }
}