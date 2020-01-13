package bi.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="withdraw")
public class Withdraw {

  @Id @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  private int id;

  @Column(name="total_amount")
  private double totalAmount = 0.0;

  @Column(name="time")
  private Date time = new Date();

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
  private Transaction transaction;

  @OneToMany(mappedBy = "withdraw", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<BillCollection> bills = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_iban", referencedColumnName = "iban")
  private Account account;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_number", referencedColumnName = "card_number")
  private Card card;

  public Withdraw() {
  }

  public Withdraw(double totalAmount, Date time, Transaction transaction, List<BillCollection> bills, Account account, Card card) {
    this.totalAmount = totalAmount;
    this.time = time;
    this.transaction = transaction;
    this.bills = bills;
    this.account = account;
    this.card = card;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }
}
