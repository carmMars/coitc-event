package it.axiid.oitc.games.listeners.events;

import it.axiid.oitc.OITC;
import it.axiid.oitc.games.Game;
import it.axiid.oitc.games.states.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class GamePlayerQuit implements Listener
{

    private final OITC instance;

    public GamePlayerQuit(OITC instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Game.getPlayingPlayers().remove(player);

        if(Game.getPlayingPlayers().size() <= instance.getConfig().getInt("min-players")) {

            if(Game.getGameState().equals(GameState.STARTING)) {
                player.sendMessage("§cAvvio dell'evento cancellato.");
            }

        }


    }

}
