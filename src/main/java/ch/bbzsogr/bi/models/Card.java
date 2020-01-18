package ch.bbzsogr.bi.models;

import ch.bbzsogr.bi.utils.CreditCardUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
public class Card {

  @Id
  @Column(name = "card_number")
  private String cardNumber = CreditCardUtil.generateCardNumber();

  @Column(name = "pin")
  private String pin = CreditCardUtil.generatePin();

  @Column(name = "expires_at")
  private Date expiresAt = Date.from(new Date().toInstant().plus(Duration.ofDays(365 * 4)));

  @Column(name = "locked", columnDefinition = "tinyint default false")
  private boolean locked = false;

  @Column(name = "credit")
  private double credit = 2000.0;

  @Column(name = "max_withdraw")
  private double maxWithdraw = 2000.0;

  @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Withdraw> withdraws = new ArrayList<>();

  @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Transaction> transactions = new ArrayList<>();

  @JsonIgnore
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_iban", referencedColumnName = "iban", nullable = false)
  private Account account;

  public Card() {
  }

  public Card(String cardNumber, String pin, Date expiresAt, boolean locked, double credit, double maxWithdraw, List<Withdraw> withdraws, List<Transaction> transactions, Account account) {
    this.cardNumber = cardNumber;
    this.pin = pin;
    this.expiresAt = expiresAt;
    this.locked = locked;
    this.credit = credit;
    this.maxWithdraw = maxWithdraw;
    this.withdraws = withdraws;
    this.transactions = transactions;
    this.account = account;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public Date getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(Date expiresAt) {
    this.expiresAt = expiresAt;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public double getCredit() {
    return credit;
  }

  public void setCredit(double credit) {
    this.credit = credit;
  }

  public double getMaxWithdraw() {
    return maxWithdraw;
  }

  public void setMaxWithdraw(double maxWithdraw) {
    this.maxWithdraw = maxWithdraw;
  }

  public List<Withdraw> getWithdraws() {
    return withdraws;
  }

  public void setWithdraws(List<Withdraw> withdraws) {
    this.withdraws = withdraws;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  @PostLoad()
  public void afterLoad() {
    this.setPin(null);
  }
}
