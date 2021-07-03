package tr.beyazpolis.database.manager;

import java.util.ArrayList;
import java.util.List;
import tr.beyazpolis.database.registery.Feature;
import tr.beyazpolis.database.registery.FeatureData;
import tr.beyazpolis.database.registery.example.features.CoinShopFeature;

public class RegisterManager {

  private static final List<Feature<?>> featureList = new ArrayList<>();
  private final List<FeatureData> featureDataList;

  public RegisterManager(){
    this.featureDataList = new ArrayList<>();
  }

  public List<Feature<?>> getFeatureList() {
    return featureList;
  }

  public List<FeatureData> getFeatureDataList() {
    return featureDataList;
  }

  public RegisterManager start(){
    featureList.add(new CoinShopFeature());
    return this;
  }

  public static void register(Feature<?> feature){
    featureList.add(feature.load());
  }

}
