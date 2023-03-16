package com.oitc.games.listeners.events;

import com.oitc.bean.PlayingPlayer;
import com.oitc.games.Game;
import com.oitc.games.states.GameState;
import com.oitc.utils.PlayingPlayerUtils;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class GameArrowOneShot implements Listener {
    @EventHandler
    public void onShot(EntityDamageByEntityEvent event) {
        if(Game.getGameState() != (GameState.PLAYING)) {
            event.setCancelled(true);
        }

        //se il player danneggiato è in spawnkill cooldown
        //in quel caso non puo hittare ed essere hittato

        //controlla se il player che ha subito danno è in cooldown
        if(event.getEntity() instanceof Player) {
            PlayingPlayer player = PlayingPlayerUtils.getPlayer((Player) event.getEntity());
            if(System.currentTimeMillis() - player.getCooldown() < 100) {
                event.setCancelled(true);
            }
        }

        //controlla se il player che è in cooldown ha tirato una freccia o vuole hittare qualcuno

        //se il player in cooldown colpisce un altro player
        if(event.getDamager() instanceof Player) {
            PlayingPlayer player = PlayingPlayerUtils.getPlayer((Player) event.getDamager());
            if(System.currentTimeMillis() - player.getCooldown() < 100) {
                event.setCancelled(true);
            }
        }
        //se il player in cooldown scocca una freccia ad un altro player
        if(event.getDamager() instanceof Arrow) {
            PlayingPlayer player = PlayingPlayerUtils.getPlayer((Player) ((Arrow) event.getDamager()).getShooter());
            if(System.currentTimeMillis() - player.getCooldown() < 100) {
                event.setCancelled(true);
            }
            //se cosi non fosse e la causa di danno è una freccia setta il danno della freccia che one shotta
            else {
                event.setDamage(100);
            }
        }


    }
}
