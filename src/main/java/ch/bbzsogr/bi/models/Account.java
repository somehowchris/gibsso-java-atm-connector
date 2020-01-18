package ch.bbzsogr.bi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.iban4j.CountryCode;
import org.iban4j.Iban;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

  @Id
  private String iban = Iban.random(CountryCode.CH).toFormattedString();

  @Column(name = "balance")
  private double balance = 100.0;

  @Column(name = "locked", columnDefinition = "tinyint default false")
  private boolean locked = false;

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Card> cards = new ArrayList<>();

  @OneToMany(mappedBy = "from", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Transaction> transactions = new ArrayList<>();

  @OneToMany(mappedBy = "to", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Transaction> inComingTransaction = new ArrayList<>();

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Withdraw> withdraws = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
  private Person person;

  public Account() {
  }

  public Account(String iban, double balance, boolean locked, List<Card> cards, List<Transaction> transactions, List<Transaction> inComingTransaction, List<Withdraw> withdraws, Person person) {
    this.iban = iban;
    this.balance = balance;
    this.locked = locked;
    this.cards = cards;
    this.transactions = transactions;
    this.inComingTransaction = inComingTransaction;
    this.withdraws = withdraws;
    this.person = person;
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public List<Card> getCards() {
    return cards;
  }

  public void setCards(List<Card> cards) {
    this.cards = cards;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public List<Transaction> getInComingTransaction() {
    return inComingTransaction;
  }

  public void setInComingTransaction(List<Transaction> inComingTransaction) {
    this.inComingTransaction = inComingTransaction;
  }

  public List<Withdraw> getWithdraws() {
    return withdraws;
  }

  public void setWithdraws(List<Withdraw> withdraws) {
    this.withdraws = withdraws;
  }

  @JsonIgnore
  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }
}
