package com.ibm.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.ibm.model.CardTransaction;
import com.ibm.repository.CardTransactionRepository;
import com.ibm.service.CardTransactionService;

import io.quarkus.panache.common.Sort;

public class CardTransactionServiceImpl implements CardTransactionService {

    @Inject
    CardTransactionRepository cardTransactionRepository;

    @Override
    public List<CardTransaction> getAllCardTransactions() {
        return cardTransactionRepository.listAll(Sort.by("timestamp"));
    }

    @Override
    public CardTransaction getCardTransactionById(Long id) {
        return cardTransactionRepository.findById(id);
    }

    @Override
    public CardTransaction createCardTransaction(CardTransaction cardTransaction) {
        cardTransactionRepository.persist(cardTransaction);
        return cardTransaction;
    }

    @Override
    public CardTransaction updateCardTransaction(CardTransaction cardTransaction) {
        CardTransaction existingCardTransaction = cardTransactionRepository.findById(id);
        existingCardTransaction.setCommerceName(cardTransaction.getCommerceName());
        existingCardTransaction.setAmount(cardTransaction.getAmount());
        existingCardTransaction.setCardNumber(cardTransaction.getCardNumber());
        existingCardTransaction.setCustomerId(cardTransaction.getCustomerId());
        existingCardTransaction.setAccountId(cardTransaction.getAccountId());
        existingCardTransaction.setStatus(cardTransaction.getStatus());
        existingCardTransaction.setType(cardTransaction.getType());

        return existingCardTransaction;
    }

    @Override
    public void deleteCardTransaction(Long id) {
        CardTransaction existingCardTransaction = cardTransactionRepository.findById(id);
        cardTransactionRepository.delete(existingCardTransaction);
    }

    

}
