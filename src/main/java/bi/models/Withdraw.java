package bi.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Withdraw {

  private double totalAmount = 0.0;
  private Date time = new Date();
  private Transaction transaction;

  private List<BillCollection> bills = new ArrayList<>();

  public Withdraw() {
  }

  public Withdraw(double totalAmount, Date time, Transaction transaction, List<BillCollection> bills) {
    this.totalAmount = totalAmount;
    this.time = time;
    this.transaction = transaction;
    this.bills = bills;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  public List<BillCollection> getBills() {
    return bills;
  }

  public void setBills(List<BillCollection> bills) {
    this.bills = bills;
  }
}
