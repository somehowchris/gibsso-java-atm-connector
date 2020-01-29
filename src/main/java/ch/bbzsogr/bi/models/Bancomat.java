package ch.bbzsogr.bi.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Bancomat.
 */
@Entity
@Table(name = "bancomat")
public class Bancomat {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "id")
  private String id;

  @Column(name = "location", unique = true)
  private String location;

  @OneToMany(mappedBy = "bancomat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<BillCollection> billCollections = new ArrayList<>();

  @OneToMany(mappedBy = "bancomat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Withdraw> withdraws = new ArrayList<>();

    /**
     * Instantiates a new Bancomat.
     */
    public Bancomat() {
  }

    /**
     * Instantiates a new Bancomat.
     *
     * @param location        the location
     * @param billCollections the bill collections
     * @param withdraws       the withdraws
     */
    public Bancomat(String location, List<BillCollection> billCollections, List<Withdraw> withdraws) {
    this.location = location;
    this.billCollections = billCollections;
    this.withdraws = withdraws;
  }

    /**
     * Gets withdraws.
     *
     * @return the withdraws
     */
    public List<Withdraw> getWithdraws() {
    return withdraws;
  }

    /**
     * Sets withdraws.
     *
     * @param withdraws the withdraws
     */
    public void setWithdraws(List<Withdraw> withdraws) {
    this.withdraws = withdraws;
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
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
    return location;
  }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
    this.location = location;
  }

    /**
     * Gets bill collections.
     *
     * @return the bill collections
     */
    public List<BillCollection> getBillCollections() {
    return billCollections;
  }

    /**
     * Sets bill collections.
     *
     * @param billCollections the bill collections
     */
    public void setBillCollections(List<BillCollection> billCollections) {
    this.billCollections = billCollections;
  }
}
