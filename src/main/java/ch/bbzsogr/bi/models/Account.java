package ch.bbzsogr.bi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.iban4j.CountryCode;
import org.iban4j.Iban;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Account.
 */
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

    /**
     * Instantiates a new Account.
     */
    public Account() {
  }

    /**
     * Instantiates a new Account.
     *
     * @param iban                the iban
     * @param balance             the balance
     * @param locked              the locked
     * @param cards               the cards
     * @param transactions        the transactions
     * @param inComingTransaction the in coming transaction
     * @param withdraws           the withdraws
     * @param person              the person
     */
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

    /**
     * Gets iban.
     *
     * @return the iban
     */
    public String getIban() {
    return iban;
  }

    /**
     * Sets iban.
     *
     * @param iban the iban
     */
    public void setIban(String iban) {
    this.iban = iban;
  }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public double getBalance() {
    return balance;
  }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(double balance) {
    this.balance = balance;
  }

    /**
     * Is locked boolean.
     *
     * @return the boolean
     */
    public boolean isLocked() {
    return locked;
  }

    /**
     * Sets locked.
     *
     * @param locked the locked
     */
    public void setLocked(boolean locked) {
    this.locked = locked;
  }

    /**
     * Gets cards.
     *
     * @return the cards
     */
    public List<Card> getCards() {
    return cards;
  }

    /**
     * Sets cards.
     *
     * @param cards the cards
     */
    public void setCards(List<Card> cards) {
    this.cards = cards;
  }

    /**
     * Gets transactions.
     *
     * @return the transactions
     */
    public List<Transaction> getTransactions() {
    return transactions;
  }

    /**
     * Sets transactions.
     *
     * @param transactions the transactions
     */
    public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

    /**
     * Gets in coming transaction.
     *
     * @return the in coming transaction
     */
    public List<Transaction> getInComingTransaction() {
    return inComingTransaction;
  }

    /**
     * Sets in coming transaction.
     *
     * @param inComingTransaction the in coming transaction
     */
    public void setInComingTransaction(List<Transaction> inComingTransaction) {
    this.inComingTransaction = inComingTransaction;
  }

    /**
     * Gets withdraws.
     *
     * @return the withdraws
     */
    public List<Withdraw> getWithdraws() {
    return withdraws;
  }

    /**
     * Sets withdraws.
     *
     * @param withdraws the withdraws
     */
    public void setWithdraws(List<Withdraw> withdraws) {
    this.withdraws = withdraws;
  }

    /**
     * Gets person.
     *
     * @return the person
     */
    @JsonIgnore
  public Person getPerson() {
    return person;
  }

    /**
     * Sets person.
     *
     * @param person the person
     */
    public void setPerson(Person person) {
    this.person = person;
  }
}
