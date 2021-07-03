package tr.beyazpolis.database.profile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import tr.beyazpolis.database.registery.Feature;
import tr.beyazpolis.database.registery.FeatureData;

public class PlayerProfile {

  private final Map<Class<? extends Feature<?>>, FeatureData> map;
  private final UUID uuid;

  public PlayerProfile(final UUID uuid) {
    this.uuid = uuid;
    this.map = new HashMap<>();
  }

  public <D extends FeatureData, F extends Feature<D>> void addFeature(Class<F> cls, D data){
    map.put(cls,data);
  }

  public <D> FeatureData getFeatureData(Class<? extends Feature> featureDataClass){
    if (getFeatureByType(featureDataClass) != null){
      return getFeatureByType(featureDataClass);
    } else {
      throw new NullPointerException("Null olmaması gereken bu getFeatureData bir sorundan dolayı hata veriyor olabilir - NullPointerException");
    }
  }

  public Map<Class<? extends Feature<?>>, FeatureData> getMap() {
    return map;
  }

  private FeatureData getFeatureByType(Class<? extends Feature> cls) {
    if (!this.map.containsKey(cls))
      return null;
    else {
      return map.get(cls);
    }
  }

  public UUID getUuid() {
    return uuid;
  }
}
