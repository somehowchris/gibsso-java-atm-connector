package ch.bbzsogr.bi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "withdraw")
public class Withdraw {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "id")
  private String id;

  @Column(name = "total_amount")
  private double totalAmount = 0.0;

  @Column(name = "time")
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bancomat_at", referencedColumnName = "id")
  private Bancomat bancomat;

  public Withdraw() {
  }

  public Withdraw(double totalAmount, Date time, Transaction transaction, List<BillCollection> bills, Account account, Card card, Bancomat bancomat) {
    this.totalAmount = totalAmount;
    this.time = time;
    this.transaction = transaction;
    this.bills = bills;
    this.account = account;
    this.card = card;
    this.bancomat = bancomat;
  }

  @JsonIgnore
  public Bancomat getBancomat() {
    return bancomat;
  }

  public void setBancomat(Bancomat bancomat) {
    this.bancomat = bancomat;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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

  @JsonIgnore
  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  @JsonIgnore
  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }
}
