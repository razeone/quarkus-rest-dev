package com.ibm.repository;

import com.ibm.model.CardTransaction;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CardTransactionRepository implements PanacheRepository<CardTransaction> {

}