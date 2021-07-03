package tr.beyazpolis.database.manager;

import tr.beyazpolis.database.TMCDatabase;

public class LibManager {

  public static LibManager libManager;

  private final TMCDatabase database;

  public LibManager(final TMCDatabase tmcDatabase){
    this.database = tmcDatabase;
  }

  public TMCDatabase getDatabase() {
    return database;
  }

  public static LibManager getLibManager() {
    return libManager;
  }

}
