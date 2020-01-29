package ch.bbzsogr.bi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Withdraw.
 */
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

    /**
     * Instantiates a new Withdraw.
     */
    public Withdraw() {
  }

    /**
     * Instantiates a new Withdraw.
     *
     * @param totalAmount the total amount
     * @param time        the time
     * @param transaction the transaction
     * @param bills       the bills
     * @param account     the account
     * @param card        the card
     * @param bancomat    the bancomat
     */
    public Withdraw(double totalAmount, Date time, Transaction transaction, List<BillCollection> bills, Account account, Card card, Bancomat bancomat) {
    this.totalAmount = totalAmount;
    this.time = time;
    this.transaction = transaction;
    this.bills = bills;
    this.account = account;
    this.card = card;
    this.bancomat = bancomat;
  }

    /**
     * Gets bancomat.
     *
     * @return the bancomat
     */
    @JsonIgnore
  public Bancomat getBancomat() {
    return bancomat;
  }

    /**
     * Sets bancomat.
     *
     * @param bancomat the bancomat
     */
    public void setBancomat(Bancomat bancomat) {
    this.bancomat = bancomat;
  }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
    return id;
  }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
    this.id = id;
  }

    /**
     * Gets total amount.
     *
     * @return the total amount
     */
    public double getTotalAmount() {
    return totalAmount;
  }

    /**
     * Sets total amount.
     *
     * @param totalAmount the total amount
     */
    public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }

    /**
     * Gets time.
     *
     * @return the time
     */
    public Date getTime() {
    return time;
  }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(Date time) {
    this.time = time;
  }

    /**
     * Gets transaction.
     *
     * @return the transaction
     */
    public Transaction getTransaction() {
    return transaction;
  }

    /**
     * Sets transaction.
     *
     * @param transaction the transaction
     */
    public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

    /**
     * Gets bills.
     *
     * @return the bills
     */
    public List<BillCollection> getBills() {
    return bills;
  }

    /**
     * Sets bills.
     *
     * @param bills the bills
     */
    public void setBills(List<BillCollection> bills) {
    this.bills = bills;
  }

    /**
     * Gets account.
     *
     * @return the account
     */
    @JsonIgnore
  public Account getAccount() {
    return account;
  }

    /**
     * Sets account.
     *
     * @param account the account
     */
    public void setAccount(Account account) {
    this.account = account;
  }

    /**
     * Gets card.
     *
     * @return the card
     */
    @JsonIgnore
  public Card getCard() {
    return card;
  }

    /**
     * Sets card.
     *
     * @param card the card
     */
    public void setCard(Card card) {
    this.card = card;
  }
}
