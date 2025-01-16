package com.dhiraj.ecommerce_multivendor.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhiraj.ecommerce_multivendor.Modals.Order;
import com.dhiraj.ecommerce_multivendor.Modals.Seller;
import com.dhiraj.ecommerce_multivendor.Modals.Transaction;
import com.dhiraj.ecommerce_multivendor.Service.SellerService;
import com.dhiraj.ecommerce_multivendor.Service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final SellerService sellerService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Order order) {
        Transaction transaction = transactionService.createTransaction(order);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/seller")
    public ResponseEntity<List<Transaction>> getTransactionBySeller(
            @RequestHeader("Authorization") String jwt) throws Exception {
        Seller seller = sellerService.getSellerProfile(jwt);

        List<Transaction> transactions = transactionService.getTransactionsBySellerId(seller);

        return ResponseEntity.ok(transactions);

    }

    @GetMapping()
    public ResponseEntity<List<Transaction>> getAllTransaction() {
        List<Transaction> transactions = transactionService.getAllTransaction();
        return ResponseEntity.ok(transactions);
    }
}
