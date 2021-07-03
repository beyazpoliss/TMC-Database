package tr.beyazpolis.database.manager;

import java.util.ArrayList;
import java.util.List;

public class CollectionManager {

  private static final List<String> collectionList = new ArrayList<>();;
  private final String name;

  public CollectionManager(final String name){
    this.name = name;
  }

  public CollectionManager load(){
    collectionList.add(name);
    return this;
  }

  public static void createCollections(final MongoManager mongoManager){
    mongoManager.getCollections().forEach(mongoCollection -> mongoManager.getMongoClient().getDatabase("TMCDatabase").getCollection(mongoCollection));
  }

  public String getName() {
    return name;
  }

  public static List<String> collectionList(){
    return collectionList;
  }
}
