package it.axiid.oitc.games.spectator;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Spectator
{

    private static final ArrayList<Player> spectators = new ArrayList<>();

    public static void setSpectator(Player player, boolean value) {
        if(value) {
            spectators.add(player);
            return;
        }
        spectators.remove(player);
    }

    public static ArrayList<Player> getSpectators() {
        return spectators;
    }

    public static boolean isSpectator(Player player) {
        return spectators.contains(player);
    }

}
