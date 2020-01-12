package bi.models;

import bi.models.enums.Currency;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class BillCollection {

  @Id
  @GeneratedValue(generator = "uuid")
  private String id;

  private double worth = 0.0;
  private int amount = 0;

  private Currency currency = Currency.CHF;

  public BillCollection(double worth, int amount, Currency currency) {
    this.worth = worth;
    this.amount = amount;
    this.currency = currency;
  }

  public BillCollection() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public double getWorth() {
    return worth;
  }

  public void setWorth(double worth) {
    this.worth = worth;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }
}
