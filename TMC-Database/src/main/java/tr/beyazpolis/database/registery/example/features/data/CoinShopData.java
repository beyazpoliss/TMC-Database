package tr.beyazpolis.database.registery.example.features.data;

import com.mongodb.client.model.Filters;
import java.util.UUID;
import org.bson.Document;
import tr.beyazpolis.database.registery.FeatureData;
import tr.beyazpolis.database.manager.MongoManager;
import tr.beyazpolis.database.registery.example.features.CoinShopFeature;

public class CoinShopData implements FeatureData {

  private int coin;
  private UUID uuid;

  private final CoinShopFeature coinShopFeature;

  public CoinShopData(final UUID uuid,final int coin){
    this.uuid = uuid;
    this.coin = coin;
    this.coinShopFeature = new CoinShopFeature();
  }

  public int getCoin() {
    return coin;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setCoin(final int coin) {
    this.coin = coin;
  }

  public void setUuid(final UUID uuid) {
    this.uuid = uuid;
  }

  @Override
  public void save(final MongoManager mongoManager, final UUID uuid) {
    final Document found = mongoManager.getDatabase().getCollection(coinShopFeature.getCollectionManager().getName()).find(Filters.eq("uuid", uuid.toString())).first();
    if (found == null){
      final CoinShopData coinShopData = new CoinShopData(uuid,0);
      Document document = new Document("uuid",uuid.toString());
      document.append("coin",coinShopData.getCoin());
      mongoManager.getDatabase().getCollection(coinShopFeature.getCollectionManager().getName()).insertOne(document);
    } else {
      final Document updateValue = new Document("coin",coin);
      final Document updater = new Document("$set",updateValue);
      mongoManager.getDatabase().getCollection(coinShopFeature.getCollectionManager().getName()).updateOne(found, updater);
    }
  }

  public CoinShopFeature getCoinShopFeature() {
    return coinShopFeature;
  }
}