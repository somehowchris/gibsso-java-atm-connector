package bi.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Card {

  @Id
  private String cardNumber;

  @Column(nullable = false)
  private String pin;

  private Date expiresAt = Date.from(new Date().toInstant().plus(Duration.ofDays(365*4)));
  private boolean locked = false;
  private double maxWithdraw = 2000.0;
  private double credit = 2000.0;

  private List<Withdraw> withdraws = new ArrayList<>();
  private List<Transaction> transactions = new ArrayList<>();

  public Card(String cardNumber, String pin, Date expiresAt, boolean locked, double maxWithdraw, double credit, List<Withdraw> withdraws, List<Transaction> transactions) {
    this.cardNumber = cardNumber;
    this.pin = pin;
    this.expiresAt = expiresAt;
    this.locked = locked;
    this.maxWithdraw = maxWithdraw;
    this.credit = credit;
    this.withdraws = withdraws;
    this.transactions = transactions;
  }

  public Card() {
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

  public double getMaxWithdraw() {
    return maxWithdraw;
  }

  public void setMaxWithdraw(double maxWithdraw) {
    this.maxWithdraw = maxWithdraw;
  }

  public double getCredit() {
    return credit;
  }

  public void setCredit(double credit) {
    this.credit = credit;
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
}
