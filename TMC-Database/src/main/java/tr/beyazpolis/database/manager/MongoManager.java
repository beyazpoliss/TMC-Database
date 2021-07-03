package tr.beyazpolis.database.manager;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;

public class MongoManager {

  private final MongoClient mongoClient;

  private final MongoDatabase database;

  private final List<String> collections;

  public MongoManager(final MongoClient mongoClient, final MongoDatabase database) {
    this.mongoClient = mongoClient;
    this.database = database;
    this.collections = new ArrayList<>();
  }

  public MongoClient getMongoClient() {
    return mongoClient;
  }

  public MongoDatabase getDatabase() {
    return database;
  }

  public List<String> getCollections() {
    return collections;
  }
}
