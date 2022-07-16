package com.ibm.service;

import java.util.List;

import com.ibm.model.CardTransaction;

public interface CardTransactionService {
    
    List<CardTransaction> getAllCardTransactions();
    CardTransaction getCardTransactionById(Long id);
    CardTransaction createCardTransaction(CardTransaction cardTransaction);
    CardTransaction updateCardTransaction(Long id, CardTransaction cardTransaction);
    void deleteCardTransaction(Long id);
}
