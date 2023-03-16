package com.oitc.expansion;

import com.oitc.OITC;
import com.oitc.games.Game;
import com.oitc.utils.PlayingPlayerUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholdersExtension extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "lyzardoitc";
    }

    @Override
    public @NotNull String getAuthor() {
        return "CARTERHH";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player p, String params) {
        if(params.equalsIgnoreCase("kills")) {
            return String.valueOf(PlayingPlayerUtils.getPlayer(p).getKills());
        } else if(params.equalsIgnoreCase("gamestate")) {
            return String.valueOf(Game.getGameState());
        } else if(params.equalsIgnoreCase("maxplayers")) {
            return String.valueOf(OITC.instance.getConfig().getInt("max-players"));
        } else if(params.equalsIgnoreCase("onlineplayers")) {
            return String.valueOf(Bukkit.getOnlinePlayers().size());
        }
        return "N.A.";
    }
}
