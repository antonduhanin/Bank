package dev5.duhanin.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cards")
public class Card extends BaseObj {
    @Column(name = "balance")
    private float balance;
    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;
    @Column(name = "state")
    private String state;
    @OneToMany(mappedBy = "card")
    private List<Transaction> transactionList;

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "id: " + id + " balance: " + balance +
                " idAccount: " + account.getId() +
                " state: " + state + '\n';
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
