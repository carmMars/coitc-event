package it.axiid.oitc.games.listeners.events;

import it.axiid.oitc.OITC;
import it.axiid.oitc.games.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class GamePlayerLogin implements Listener {

    private final OITC instance;

    public GamePlayerLogin(OITC instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        if(Game.getPlayingPlayers().size() == instance.getConfig().getInt("max-players")) {

            if(player.hasPermission("lyzardoitc.bypass")) {
                player.sendMessage("§aHai bypassato il game-full.");
                return;
            }

            event.disallow(PlayerLoginEvent.Result.KICK_FULL, "§cgame-full.");
        }

    }

}
