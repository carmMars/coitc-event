package com.oitc.games.listeners.events;

import com.oitc.games.Game;
import com.oitc.utils.PlayingPlayerUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class GamePlayerRespawnEvent implements Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if(event.getPlayer() instanceof Player) {
            Player player = event.getPlayer();
            PlayingPlayerUtils.getPlayer(player).setCooldown(System.currentTimeMillis());
            event.setRespawnLocation(Game.getSpawnLocations().get(ThreadLocalRandom.current().nextInt(0, Game.getSpawnLocations().size())));
            player.getInventory().addItem(new ItemStack(Material.WOOD_PICKAXE));
            player.getInventory().addItem(new ItemStack(Material.BOW));
            player.getInventory().addItem(new ItemStack(Material.ARROW));

        }
    }
}
