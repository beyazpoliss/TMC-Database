package tr.beyazpolis.database.registery;

import java.util.UUID;
import tr.beyazpolis.database.manager.MongoManager;

public interface Feature<T> {

   FeatureData getOrCreate(MongoManager mongoManager, UUID uuid);

   Feature load();

}
