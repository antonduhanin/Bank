package dev5.duhanin.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseObj {
    @ManyToOne
    @JoinColumn(name = "id_card")
    private Card card;
    @Column(name = "date")
    private Date date;
    @Column(name = "summa")
    private float summa;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "recipient")
    private Account recipient;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getSumma() {
        return summa;
    }

    public void setSumma(float summa) {
        this.summa = summa;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Account getRecipient() {
        return recipient;
    }

    public void setRecipient(Account recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "id: " + id + " idCard: " + card.getId() + " date: " + date +
                " summa: " + summa +
                " title: " + title + " " +
                "recipient: " + recipient + '\n';
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
