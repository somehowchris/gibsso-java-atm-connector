package ch.bbzsogr.bi.seeder;

import ch.bbzsogr.bi.decorators.Seeder;
import ch.bbzsogr.bi.exceptions.EntitySaveException;
import ch.bbzsogr.bi.interfaces.Seed;
import ch.bbzsogr.bi.models.Account;
import ch.bbzsogr.bi.models.Person;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.bi.services.PeopleService;
import ch.bbzsogr.bi.utils.Container;
import ch.bbzsogr.bi.utils.DotEnvUtil;
import ch.bbzsogr.bi.utils.HashUtil;
import com.google.common.collect.Lists;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.logging.Logger;

/**
 * The type Bank seeder.
 */
@Seeder
public class BankSeeder implements Seed {

  @Override
  public void run(Session session, DotEnvUtil dotEnv, Logger logger) throws EntitySaveException {


    PeopleService peopleService = Container.getService(PeopleService.class, ApiType.DIRECT);
    try {
      peopleService.getPersonByMail(dotEnv.get("BANK_MAIL"));

      logger.info("Bank account already exists");
    } catch (NoResultException ex){
      logger.info("Creating bank account");

      Account account = new Account();
      Person person = new Person(dotEnv.get("BANK_MAIL"), "Admin", "Bank", HashUtil.hash("ThatStrongPassword"), Lists.newArrayList(account));
      account.setPerson(person);

      peopleService.createPerson(person);

      logger.info("Bank account created");
    } catch (Exception e) {
      e.printStackTrace();

    }
  }
}
