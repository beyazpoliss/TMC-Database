package tr.beyazpolis.database.registery.example.features;

import com.mongodb.client.model.Filters;
import java.util.UUID;
import org.bson.Document;
import tr.beyazpolis.database.registery.Feature;
import tr.beyazpolis.database.registery.FeatureData;
import tr.beyazpolis.database.registery.example.features.data.CoinShopData;
import tr.beyazpolis.database.manager.CollectionManager;
import tr.beyazpolis.database.manager.MongoManager;

public class CoinShopFeature implements Feature {

  private final CollectionManager collectionManager;

  public CoinShopFeature() {
   this.collectionManager = new CollectionManager("CoinShop");
  }

  @Override
  public FeatureData getOrCreate(final MongoManager mongoManager, UUID uuid) {
    final Document found = mongoManager.getDatabase()
      .getCollection(collectionManager.getName())
      .find(Filters.eq("uuid", uuid.toString()))
      .first();
    if (found == null){
      final CoinShopData coinShopData = new CoinShopData(uuid,0);
      Document document = new Document("uuid",uuid.toString());
      document.append("coin",coinShopData.getCoin());
      mongoManager.getDatabase().getCollection(collectionManager.getName()).insertOne(document);
      return coinShopData;
    } else {
      final int coin = found.getInteger("coin");
      return new CoinShopData(uuid,coin);
    }
  }

  @Override
  public Feature<?> load() {
    collectionManager.load();
    return this;
  }

  public CollectionManager getCollectionManager() {
    return collectionManager;
  }
}
