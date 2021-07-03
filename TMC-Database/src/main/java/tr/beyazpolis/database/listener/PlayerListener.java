package tr.beyazpolis.database.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tr.beyazpolis.database.TMCDatabase;

public class PlayerListener implements Listener {

  private final TMCDatabase tmcDatabase;

  public PlayerListener(TMCDatabase tmcDatabase){
    this.tmcDatabase = tmcDatabase;
  }

  @EventHandler
  public void onJoin(AsyncPlayerPreLoginEvent event){
    if (this.tmcDatabase.getProfileManager() != null && this.tmcDatabase.getMongoManager() != null && this.tmcDatabase.getRegisterManager() != null){
      this.tmcDatabase.getProfileManager().getOrCreate(event.getUniqueId());
    } else {
      event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
      event.
        setKickMessage(
          "Evet veriler yüklenmeden giriş yapmaya çalıştınız, büyük hatalara sebep olabilirdiniz, Bu seferlik sizi affediyoruz iyi oyunlar");
    }
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    Bukkit.getScheduler().runTaskAsynchronously(tmcDatabase, () -> tmcDatabase.getProfileManager().save(event.getPlayer().getUniqueId()));
  }
}

