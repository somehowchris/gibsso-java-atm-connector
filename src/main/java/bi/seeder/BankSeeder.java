package bi.seeder;

import bi.interfaces.Seeder;
import bi.models.Account;
import bi.models.Person;
import bi.utils.HashUtil;
import com.google.common.collect.Lists;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BankSeeder implements Seeder {

  // TODO add email from dotenv
  // TODO check if already exists
  @Override
  public void run(Session session) {
    Account account = new Account();
    Person person = new Person("bank@bbzsogr.ch", "Admin", "Bank", HashUtil.hash("ThatStrongPassword"), Lists.newArrayList(account));
    account.setPerson(person);
    Transaction transaction = session.beginTransaction();
    session.save(person);
    transaction.commit();
  }
}
