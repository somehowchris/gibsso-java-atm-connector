package bi.models;

import bi.models.enums.Currency;
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

  public Withdraw getWithdraw() {
    return withdraw;
  }

  public void setWithdraw(Withdraw withdraw) {
    this.withdraw = withdraw;
  }

  public Account getTo() {
    return to;
  }

  public void setTo(Account to) {
    this.to = to;
  }

  public Account getFrom() {
    return from;
  }

  public void setFrom(Account from) {
    this.from = from;
  }

  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }
}
