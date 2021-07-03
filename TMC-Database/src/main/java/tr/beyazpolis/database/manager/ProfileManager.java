package tr.beyazpolis.database.manager;

import java.util.HashMap;
import java.util.UUID;
import tr.beyazpolis.database.registery.Feature;
import tr.beyazpolis.database.profile.PlayerProfile;

public class ProfileManager {

  private static final HashMap<UUID, PlayerProfile> databaseMap = new HashMap<>();

  private final MongoManager mongoManager;

  private final RegisterManager registerManager;

  public ProfileManager(final MongoManager mongoManager, final RegisterManager registerManager) {
    this.mongoManager = mongoManager;
    this.registerManager = registerManager;
  }

  public PlayerProfile getOrCreate(UUID uuid) {
    if (databaseMap.get(uuid) != null){
      return databaseMap.get(uuid);
    }
    final PlayerProfile playerProfile = new PlayerProfile(uuid);
    for (Feature<?> feature : registerManager.getFeatureList()){
      playerProfile.addFeature(feature.getClass(),feature.getOrCreate(mongoManager,uuid));
    }
    databaseMap.put(uuid, playerProfile);
    return playerProfile;
  }

  public void save(UUID uuid){
    final PlayerProfile playerProfile = getOrCreate(uuid);
    for (Class<? extends Feature<?>> featureData : playerProfile.getMap().keySet()){
      playerProfile.getFeatureData(featureData).save(mongoManager,uuid);
    }
    databaseMap.remove(uuid);
  }

  public RegisterManager getRegisterManager() {
    return registerManager;
  }

  public void saveAll(){
    for (UUID uuid : databaseMap.keySet()){
      save(uuid);
    }
  }
}