package bi.models;

import bi.models.enums.Currency;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class Transaction {

  @Id
  @GeneratedValue(generator = "uuid")
  private int id;

  private double amount = 0.0;
  private Date time = new Date();
  private Currency currency = Currency.CHF;
  private Account to;

  public Transaction(double amount, Date time, Currency currency, Account to) {
    this.amount = amount;
    this.time = time;
    this.currency = currency;
    this.to = to;
  }

  public Transaction() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public Account getTo() {
    return to;
  }

  public void setTo(Account to) {
    this.to = to;
  }
}
