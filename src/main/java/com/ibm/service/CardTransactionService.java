package com.ibm.service;

import java.util.List;

import com.ibm.model.CardTransaction;

public interface CardTransactionService {

    final String CARD_TRANSACTION_NOT_FOUND = "CardTransaction not found";
    final String CARD_TRANSACTION_IS_NOT_VALID_TO_CREATE = "CardTransaction is not valid to create";
    
    List<CardTransaction> getAllCardTransactions();
    CardTransaction getCardTransactionById(Long id);
    CardTransaction createCardTransaction(CardTransaction cardTransaction);
    CardTransaction updateCardTransaction(Long id, CardTransaction cardTransaction);
    void deleteCardTransaction(Long id);
}
