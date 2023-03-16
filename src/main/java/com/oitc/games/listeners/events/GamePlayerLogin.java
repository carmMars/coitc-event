package com.oitc.games.listeners.events;

import com.oitc.OITC;
import com.oitc.games.Game;
import com.oitc.games.states.GameState;
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

        if(Game.getGameState().equals(GameState.PLAYING) || Game.getGameState().equals(GameState.ENDING)) {
            if(player.hasPermission("lyzardoitc.bypass")) {
                player.sendMessage("§aHai bypassato il game gia startato.");
                return;
            }

            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§cGioco gia iniziato");
        }

        if(Game.getPlayingPlayers().size() == instance.getConfig().getInt("max-players")) {

            if(player.hasPermission("lyzardoitc.bypass")) {
                player.sendMessage("§aHai bypassato il game-full.");
                return;
            }

            event.disallow(PlayerLoginEvent.Result.KICK_FULL, "§cgame-full.");
        }

    }

}
