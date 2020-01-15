import ch.bbzsogr.bi.controllers.DatabaseController;
import ch.bbzsogr.bi.exceptions.AccessNotGrantedException;
import ch.bbzsogr.bi.exceptions.ConnectionRefusedException;
import ch.bbzsogr.bi.exceptions.OGMDatabaseTypeNotFoundException;
import ch.bbzsogr.bi.exceptions.UrlDialectNotSupported;
import ch.bbzsogr.bi.interfaces.services.AccountServiceInterface;
import ch.bbzsogr.bi.models.enums.ApiType;
import ch.bbzsogr.bi.utils.Container;

import java.io.IOException;

public class main {

  public static void main(String[] args) throws AccessNotGrantedException, ConnectionRefusedException, UrlDialectNotSupported, OGMDatabaseTypeNotFoundException, IOException {
    DatabaseController databaseController = new DatabaseController();
    AccountServiceInterface accountService = Container.getService(AccountServiceInterface.class, ApiType.DIRECT);
  }
}
