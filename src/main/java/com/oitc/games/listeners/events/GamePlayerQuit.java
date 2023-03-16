package com.oitc.games.listeners.events;

import com.oitc.OITC;
import com.oitc.games.Game;
import com.oitc.utils.PlayingPlayerUtils;
import org.bukkit.Bukkit;
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

        Game.getPlayingPlayers().remove(PlayingPlayerUtils.getPlayer(player));
        Bukkit.broadcastMessage("§e" + player.getName() + " §fè uscito §7(" + Game.getPlayingPlayers().size() + "/" + instance.getConfig().getInt("max-players") + ")");

    }

}
