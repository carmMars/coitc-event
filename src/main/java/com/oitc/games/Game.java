package com.oitc.games;

import com.oitc.OITC;
import com.oitc.bean.PlayingPlayer;
import com.oitc.games.states.GameState;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Game {
    public final static int _COUNTDOWN = 10;

    private static ArrayList<Location> locations = new ArrayList<>();

    private static ArrayList<PlayingPlayer> playingPlayers = new ArrayList<>();

    private static GameState gameState;

    public static int countdown;

    public static void addSpawnLocation(Player player) {locations.add(player.getLocation());}

    public static void addPlayingPlayer(PlayingPlayer player) {
        playingPlayers.add(player);
    }

    public static ArrayList<PlayingPlayer> getPlayingPlayers() {
        return playingPlayers;
    }

    public static ArrayList<Location> getSpawnLocations() {
        return locations;
    }

    public static void setGameState(GameState gameState) {
        Game.gameState = gameState;
    }

    public static void setLocations(ArrayList<Location> locs) {locations = locs;}

    public static GameState getGameState() {
        return gameState;
    }

    public static PlayingPlayer getTopPlayers(int position) {
        ArrayList<PlayingPlayer> sorted = playingPlayers;
        if(sorted.size() - 1 < position) {
            return null;
        }
        Collections.sort(sorted, Comparator.comparingInt(PlayingPlayer::getKills).reversed());
        return sorted.get(position);
    }



    public static boolean gameStart() {
        //controlla se si sta provando di force startare un game con 1 player
        if(playingPlayers.size() == 1) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Il game non può iniziare con cosi pochi players");
            return false;
        }
        //controlla se ci sono abbastanza punti spawn per tutti i max players settati
        if(locations != null) {
            if(OITC.instance.getConfig().getInt("max-players") > locations.size()) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Il game non può iniziare con cosi pochi punti spawn");
                return false;
            }
        }

        //teletrasporta tutti i player nei punti spawn della mappa
        int iterator = 0;
        for(PlayingPlayer player : playingPlayers) {

            player.getPlayer().teleport(locations.get(iterator));

            player.getPlayer().setGameMode(GameMode.SURVIVAL);

            player.getPlayer().getInventory().addItem(new ItemStack(Material.WOOD_PICKAXE));
            player.getPlayer().getInventory().addItem(new ItemStack(Material.BOW));
            player.getPlayer().getInventory().addItem(new ItemStack(Material.ARROW));

            iterator += 1;
        }
        return true;
    }

}
