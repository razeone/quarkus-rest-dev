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

import javax.validation.constraints.NotBlank;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Cacheable
public class CardTransaction extends PanacheEntity {

    @Column(length=100)
    @NotBlank(message="Commerce Name is required")
    private String commerceName;
    @NotBlank(message="Amount is required")
    private BigDecimal amount;
    @NotBlank(message="Card Number is required")
    private String cardNumber;
    @NotBlank(message="Customer ID is required")
    private UUID customerId;
    @NotBlank(message="Account ID is required")
    private UUID accountId;
    
    @Enumerated(EnumType.STRING)
    @NotBlank(message="Status is required")
    private CardTransactionStatus status;

    @Enumerated(EnumType.STRING)
    @NotBlank(message="Type is required")
    private CardTransactionType type;

    @Temporal(TemporalType.TIMESTAMP)
    Date timestamp;

    public CardTransaction() {
      // Empty constructor required for JPA
    }

    public Long getId() {
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

    public void setTimestampToNow() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
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

    protected boolean isValidToUpdate() {
        return this.commerceName != null && this.amount != null && this.cardNumber != null && this.customerId != null && this.accountId != null && this.status != null && this.type != null;
    }

    public boolean isValidToCreate() {
        this.id = null;
        this.setTimestampToNow();
        return this.commerceName != null && this.amount != null && this.cardNumber != null && this.customerId != null && this.accountId != null && this.status != null && this.type != null && this.timestamp != null;
    }

    public void update(CardTransaction cardTransaction) {
        if (cardTransaction.isValidToUpdate()) {
            this.commerceName = cardTransaction.commerceName;
            this.amount = cardTransaction.amount;
            this.cardNumber = cardTransaction.cardNumber;
            this.customerId = cardTransaction.customerId;
            this.accountId = cardTransaction.accountId;
            this.status = cardTransaction.status;
            this.type = cardTransaction.type;
        }
        else {
            throw new IllegalStateException("Invalid CardTransaction to update");
        }
    }

}
