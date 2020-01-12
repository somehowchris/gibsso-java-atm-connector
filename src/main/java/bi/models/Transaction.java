package bi.models;

import bi.models.enums.ORMSupportedDatabases;

import java.util.Date;

public class Transaction {
  private int id;

  private double amount;

  private Date time;

  private ORMSupportedDatabases type = ORMSupportedDatabases.MySQL;


  public Transaction(double amount, Date time) {
    this.amount = amount;
    this.time = time;
  }

  public Transaction() {
  }

  public double getAmount() {
    return amount;
  }

  public Date getTime() {
    return time;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public ORMSupportedDatabases getType() {
    return type;
  }

  public void setType(ORMSupportedDatabases type) {
    this.type = type;
  }
}
