package com.codegym.banking.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;
    private String email;
    private String phone;
    private String address;

    @Column(precision = 12, scale = 1, nullable = false)
    private BigDecimal balance;

    @OneToMany(mappedBy = "customer")
    private List<Deposit> deposit;

    @OneToMany(mappedBy = "customer")
    private List<Withdraw> withdraw;

    @OneToMany(mappedBy = "recipient")
    private List<Transfer> recipient;

    @OneToMany(mappedBy = "sender")
    private List<Transfer> sender;

    public Customer() {}

    public Customer(Long id,
                    String fullName,
                    String email,
                    String phone,
                    String address,
                    BigDecimal balance,
                    List<Deposit> deposit,
                    List<Withdraw> withdraw,
                    List<Transfer> recipient,
                    List<Transfer> sender) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
        this.deposit = deposit;
        this.withdraw = withdraw;
        this.recipient = recipient;
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Deposit> getDeposit() {
        return deposit;
    }

    public void setDeposit(List<Deposit> deposit) {
        this.deposit = deposit;
    }

    public List<Withdraw> getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(List<Withdraw> withdraw) {
        this.withdraw = withdraw;
    }

    public List<Transfer> getRecipient() {
        return recipient;
    }

    public void setRecipient(List<Transfer> recipient) {
        this.recipient = recipient;
    }

    public List<Transfer> getSender() {
        return sender;
    }

    public void setSender(List<Transfer> sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                ", deposit=" + deposit +
                ", withdraw=" + withdraw +
                ", recipient=" + recipient +
                ", sender=" + sender +
                '}' + super.toString();
    }
}
