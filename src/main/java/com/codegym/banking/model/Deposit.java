package com.codegym.banking.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "deposit")
public class Deposit extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "transaction_amount", precision = 12, scale = 1, nullable = false)
    private BigDecimal transactionAmount;

    public Deposit() {}

    public Deposit(Long id, Customer customer, BigDecimal transactionAmount) {
        this.id = id;
        this.customer = customer;
        this.transactionAmount = transactionAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", customer=" + customer +
                ", transactionAmount=" + transactionAmount +
                '}'
                + super.toString();
    }
}
