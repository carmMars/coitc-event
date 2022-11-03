package it.axiid.oitc.games;

import it.axiid.oitc.games.states.GameState;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {

    private static ArrayList<Player> playingPlayers = new ArrayList<>();
    private static GameState gameState;
    public static int countdown = 10;

    public static ArrayList<Location> locations = new ArrayList<>();

    public static void addPlayingPlayer(Player player) {
        playingPlayers.add(player);
    }

    public static ArrayList<Player> getPlayingPlayers() {
        return playingPlayers;
    }

    public static void setGameState(GameState gameState) {
        Game.gameState = gameState;
    }

    public static GameState getGameState() {
        return gameState;
    }

}
