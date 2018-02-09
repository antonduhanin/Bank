package dev5.duhanin.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account extends BaseObj {
    @Column(name = "balance")
    private float balance;
    @Column(name = "state")
    private String state;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private User user;
    @OneToMany(mappedBy = "account")
    private List<Card> cardList;
    @OneToMany(mappedBy = "recipient")
    private List<Transaction> transactionList;

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
                " state: " + state + '\n';
    }

}

