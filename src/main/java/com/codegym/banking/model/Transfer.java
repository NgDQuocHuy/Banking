package com.codegym.banking.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transfer")
public class Transfer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "INT DEFAULT 10")
    private int fees;

    @Column(name = "fees_amount", precision = 12, scale = 1, nullable = false)
    private BigDecimal feesAmount;

    @Column(name = "transaction_amount", precision = 12, scale = 1, nullable = false)
    private BigDecimal transactionAmount;

    @Column(name = "transfer_amount", precision = 12, scale = 1, nullable = false)
    private BigDecimal transferAmount;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private Customer recipient;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Customer sender;

    public Transfer() {}

    public Transfer(Long id,
                    int fees,
                    BigDecimal feesAmount,
                    BigDecimal transactionAmount,
                    BigDecimal transferAmount,
                    Customer recipient,
                    Customer sender) {
        this.id = id;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
        this.transferAmount = transferAmount;
        this.recipient = recipient;
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public BigDecimal getFeesAmount() {
        return feesAmount;
    }

    public void setFeesAmount(BigDecimal feesAmount) {
        this.feesAmount = feesAmount;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Customer getRecipient() {
        return recipient;
    }

    public void setRecipient(Customer recipient) {
        this.recipient = recipient;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", fees=" + fees +
                ", feesAmount=" + feesAmount +
                ", transactionAmount=" + transactionAmount +
                ", transferAmount=" + transferAmount +
                ", recipient=" + recipient +
                ", sender=" + sender +
                '}' + super.toString();
    }
}
