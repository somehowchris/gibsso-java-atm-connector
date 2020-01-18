package ch.bbzsogr.bi.models;

import ch.bbzsogr.bi.models.enums.Currency;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

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

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "withdraw_id", referencedColumnName = "id")
  private Withdraw withdraw;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_to_iban", referencedColumnName = "iban", nullable = false)
  private Account to;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_from_iban", referencedColumnName = "iban")
  private Account from;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_number", referencedColumnName = "card_number")
  private Card card;

  public Transaction() {
  }

  public Transaction(double amount, Date time, Currency currency, Withdraw withdraw, Account to, Account from, Card card) {
    this.amount = amount;
    this.time = time;
    this.currency = currency;
    this.withdraw = withdraw;
    this.to = to;
    this.from = from;
    this.card = card;
  }

  public Transaction(double amount, Account to, Account from) {
    this.amount = amount;
    this.to = to;
    this.from = from;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  @JsonIgnore
  public Withdraw getWithdraw() {
    return withdraw;
  }

  public void setWithdraw(Withdraw withdraw) {
    this.withdraw = withdraw;
  }

  @JsonIgnore
  public Account getTo() {
    return to;
  }

  public void setTo(Account to) {
    this.to = to;
  }

  @JsonIgnore
  public Account getFrom() {
    return from;
  }

  public void setFrom(Account from) {
    this.from = from;
  }

  @JsonIgnore
  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }
}
