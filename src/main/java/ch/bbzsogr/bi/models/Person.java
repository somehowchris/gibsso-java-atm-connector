package ch.bbzsogr.bi.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Person.
 */
@Entity
@Table(name = "person")
public class Person {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "id")
  private String id;

  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "password")
  private String password;

  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Account> accounts = new ArrayList<>();

    /**
     * Instantiates a new Person.
     */
    public Person() {
  }

    /**
     * Instantiates a new Person.
     *
     * @param email     the email
     * @param firstName the first name
     * @param lastName  the last name
     * @param password  the password
     * @param accounts  the accounts
     */
    public Person(String email, String firstName, String lastName, String password, List<Account> accounts) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.accounts = accounts;
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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
    return email;
  }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
    this.email = email;
  }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
    return firstName;
  }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
    return lastName;
  }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
    this.lastName = lastName;
  }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
    return password;
  }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
    this.password = password;
  }

    /**
     * Gets accounts.
     *
     * @return the accounts
     */
    public List<Account> getAccounts() {
    return accounts;
  }

    /**
     * Sets accounts.
     *
     * @param accounts the accounts
     */
    public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }

    /**
     * After load.
     */
    @PostLoad()
  public void afterLoad() {
    this.setPassword(null);
  }
}
