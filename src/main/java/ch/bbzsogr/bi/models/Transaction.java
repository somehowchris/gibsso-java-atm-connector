package ch.bbzsogr.bi.models;

import ch.bbzsogr.bi.models.enums.Currency;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * The type Transaction.
 */
@Entity
@Table(name = "transaction")
public class Transaction {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "id")
  private String id;

  @Column(name = "amount")
  private double amount = 0.0;

  @Column(name = "time")
  private Date time = new Date();

  @Enumerated(EnumType.STRING)
  private Currency currency = Currency.CHF;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "withdraw_id", referencedColumnName = "id")
  private Withdraw withdraw;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_to_iban", referencedColumnName = "iban", nullable = false)
  private Account to;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_from_iban", referencedColumnName = "iban")
  private Account from;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_number", referencedColumnName = "card_number")
  private Card card;

    /**
     * Instantiates a new Transaction.
     */
    public Transaction() {
  }

    /**
     * Instantiates a new Transaction.
     *
     * @param amount   the amount
     * @param time     the time
     * @param currency the currency
     * @param withdraw the withdraw
     * @param to       the to
     * @param from     the from
     * @param card     the card
     */
    public Transaction(double amount, Date time, Currency currency, Withdraw withdraw, Account to, Account from, Card card) {
    this.amount = amount;
    this.time = time;
    this.currency = currency;
    this.withdraw = withdraw;
    this.to = to;
    this.from = from;
    this.card = card;
  }

    /**
     * Instantiates a new Transaction.
     *
     * @param amount the amount
     * @param to     the to
     * @param from   the from
     */
    public Transaction(double amount, Account to, Account from) {
    this.amount = amount;
    this.to = to;
    this.from = from;
  }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
    return id;
  }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
    this.id = id;
  }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public double getAmount() {
    return amount;
  }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(double amount) {
    this.amount = amount;
  }

    /**
     * Gets time.
     *
     * @return the time
     */
    public Date getTime() {
    return time;
  }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(Date time) {
    this.time = time;
  }

    /**
     * Gets currency.
     *
     * @return the currency
     */
    public Currency getCurrency() {
    return currency;
  }

    /**
     * Sets currency.
     *
     * @param currency the currency
     */
    public void setCurrency(Currency currency) {
    this.currency = currency;
  }

    /**
     * Gets withdraw.
     *
     * @return the withdraw
     */
    @JsonIgnore
  public Withdraw getWithdraw() {
    return withdraw;
  }

    /**
     * Sets withdraw.
     *
     * @param withdraw the withdraw
     */
    public void setWithdraw(Withdraw withdraw) {
    this.withdraw = withdraw;
  }

    /**
     * Gets to.
     *
     * @return the to
     */
    @JsonIgnore
  public Account getTo() {
    return to;
  }

    /**
     * Sets to.
     *
     * @param to the to
     */
    public void setTo(Account to) {
    this.to = to;
  }

    /**
     * Gets from.
     *
     * @return the from
     */
    @JsonIgnore
  public Account getFrom() {
    return from;
  }

    /**
     * Sets from.
     *
     * @param from the from
     */
    public void setFrom(Account from) {
    this.from = from;
  }

    /**
     * Gets card.
     *
     * @return the card
     */
    @JsonIgnore
  public Card getCard() {
    return card;
  }

    /**
     * Sets card.
     *
     * @param card the card
     */
    public void setCard(Card card) {
    this.card = card;
  }
}
