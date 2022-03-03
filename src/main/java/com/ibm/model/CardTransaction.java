package com.ibm.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.math.BigDecimal;

@Entity
@Cacheable
public class CardTransaction extends PanacheEntity {

    @Column(length=100)
    private String commerceName;
    
    private BigDecimal amount;
    private String cardNumber;
    private UUID customerId;
    private UUID accountId;
    
    @Enumerated(EnumType.STRING)
    private CardTransactionStatus status;

    @Enumerated(EnumType.STRING)
    private CardTransactionType type;

    @Temporal(TemporalType.TIMESTAMP)
    Date timestamp;

    public CardTransaction() {
      // Empty constructor required for JPA
    }

    public Long getid() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommerceName() {
        return this.commerceName;
    }

    public void setCommerceName(String commerceName) {
        this.commerceName = commerceName;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public UUID getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public CardTransactionStatus getStatus() {
        return this.status;
    }

    public void setStatus(CardTransactionStatus status) {
        this.status = status;
    }

    public CardTransactionType getType() {
        return this.type;
    }

    public void setType(CardTransactionType type) {
        this.type = type;
    }

}
