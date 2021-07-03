package tr.beyazpolis.database.registery;

import java.util.UUID;
import tr.beyazpolis.database.manager.MongoManager;

public interface FeatureData {

  void save(MongoManager mongoManager, UUID uuid);
}
