package com.ibm.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.ibm.model.CardTransaction;
import com.ibm.repository.CardTransactionRepository;
import com.ibm.service.CardTransactionService;

import io.quarkus.panache.common.Sort;

@ApplicationScoped
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
    public CardTransaction updateCardTransaction(Long id, CardTransaction cardTransaction) {
        CardTransaction existingCardTransaction = cardTransactionRepository.findById(id);
        
        if(existingCardTransaction == null) {
            throw new IllegalArgumentException("CardTransaction not found");
        }
        existingCardTransaction.update(cardTransaction);
        cardTransactionRepository.persist(existingCardTransaction);
        return existingCardTransaction;
    }

    @Override
    public void deleteCardTransaction(Long id) {
        CardTransaction existingCardTransaction = cardTransactionRepository.findById(id);
        if(existingCardTransaction == null) {
            throw new IllegalArgumentException("CardTransaction not found");
        }
        cardTransactionRepository.delete(existingCardTransaction);
    }

    

}
