package bi.interfaces;

import org.hibernate.Session;

public interface Seeder {
  void run(Session session);
}
