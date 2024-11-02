package com.dhiraj.ecommerce_multivendor.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhiraj.ecommerce_multivendor.Modals.Order;
import com.dhiraj.ecommerce_multivendor.Modals.Seller;
import com.dhiraj.ecommerce_multivendor.Modals.Transaction;
import com.dhiraj.ecommerce_multivendor.Repository.SellerRepositiory;
import com.dhiraj.ecommerce_multivendor.Repository.TransactionRepository;
import com.dhiraj.ecommerce_multivendor.Service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImp implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final SellerRepositiory sellerRepositiory;

    @Override
    public Transaction createTransaction(Order order) {
        Seller seller = sellerRepositiory.findById(order.getSellerId()).get();
        Transaction transaction = new Transaction();
        transaction.setSeller(seller);
        transaction.setCustomer(order.getUser());
        transaction.setOrder(order);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsBySellerId(Seller seller) {
        return transactionRepository.findBySellerId(seller.getId());
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

}
