package ch.bbzsogr.bi.models.enums;

import ch.bbzsogr.bi.exceptions.OGMDatabaseTypeNotFoundException;

/**
 * The enum Ogm supported databases.
 */
public enum OGMSupportedDatabases {
  /**
   * Mongo db ogm supported databases.
   */
  MongoDB("MONGODB"),
  /**
   * Cassandra ogm supported databases.
   */
  Cassandra("CASSANDRA"),
  /**
   * Couch db ogm supported databases.
   */
  CouchDB("COUCHDB"),
  /**
   * Redis ogm supported databases.
   */
  Redis("REDIS");

  private String name;

  OGMSupportedDatabases(String name) {
    this.name = name;
  }

  /**
   * Gets type by name.
   *
   * @param name the name
   * @return the type by name
   * @throws OGMDatabaseTypeNotFoundException the ogm database type not found exception
   */
  public static OGMSupportedDatabases getTypeByName(String name) throws OGMDatabaseTypeNotFoundException {
    if (name.equals(MongoDB.getName())) {
      return MongoDB;
    } else if (name.equals(Cassandra.getName())) {
      return Cassandra;
    } else if (name.equals(CouchDB.getName())) {
      return CouchDB;
    } else if (name.equals(Redis.getName())) {
      return Redis;
    }
    throw new OGMDatabaseTypeNotFoundException(name);
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }
}
