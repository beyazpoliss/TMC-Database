package tr.beyazpolis.database;

import com.avaje.ebean.validation.NotNull;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.lang.Nullable;
import org.bukkit.plugin.java.JavaPlugin;
import tr.beyazpolis.database.listener.PlayerListener;
import tr.beyazpolis.database.manager.CollectionManager;
import tr.beyazpolis.database.manager.LibManager;
import tr.beyazpolis.database.manager.MongoManager;
import tr.beyazpolis.database.manager.ProfileManager;
import tr.beyazpolis.database.manager.RegisterManager;

public class TMCDatabase extends JavaPlugin {

  @Nullable
  private MongoManager mongoManager;

  @Nullable
  private ProfileManager profileManager;

  @Nullable
  private RegisterManager registerManager;

  @Override
  public void onEnable() {
    LibManager.libManager = new LibManager(this);
    getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

    final String passwordString = "PASSWORD BRO";

    final MongoClient mongoClient = MongoClients.create(passwordString);
    final MongoDatabase database = mongoClient.getDatabase("TMCDatabase");

    this.mongoManager = new MongoManager(mongoClient,database);
    this.registerManager = new RegisterManager().start();
    this.profileManager = new ProfileManager(mongoManager,registerManager);

    CollectionManager.createCollections(mongoManager);
  }

  @Override
  public void onDisable() {
    this.getProfileManager().saveAll();
  }

  @NotNull
  public ProfileManager getProfileManager(){
    if (this.profileManager == null){
      this.profileManager = new ProfileManager(mongoManager,registerManager);
    }
    return profileManager;
  }

  @NotNull
  public MongoManager getMongoManager() {
    if (this.mongoManager == null){
      final String passwordString = "PASSWORD BRO";
      final MongoClient mongoClient = MongoClients.create(passwordString);
      final MongoDatabase database = mongoClient.getDatabase("TMCDatabase");
      this.mongoManager = new MongoManager(mongoClient,database);
    }
    return mongoManager;
  }

  @NotNull
  public RegisterManager getRegisterManager() {
    if (this.mongoManager == null){
      this.registerManager = new RegisterManager();
    }
    return registerManager;
  }
}
