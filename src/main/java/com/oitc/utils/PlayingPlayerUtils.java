package com.oitc.utils;

import com.oitc.bean.PlayingPlayer;
import com.oitc.games.Game;
import org.bukkit.entity.Player;

public class PlayingPlayerUtils {
    public static PlayingPlayer getPlayer(Player player) {
        for(PlayingPlayer playingPlayer : Game.getPlayingPlayers()) {
            if(playingPlayer.getPlayer().getUniqueId().equals(player.getUniqueId())) return playingPlayer;
        }
        return null;
    }
}
