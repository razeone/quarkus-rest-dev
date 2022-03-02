package com.ibm.model;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.math.BigDecimal;

@Entity
@Cacheable
public class CardTransaction extends PanacheEntity{

    @Column(length=100)
    private String commerceName;
    
    private BigDecimal amount;
    private String cardNumber;
    private Boolean processed;

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

    public Boolean isProcessed() {
        return this.processed;
    }

    public Boolean getProcessed() {
        return this.processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
}
