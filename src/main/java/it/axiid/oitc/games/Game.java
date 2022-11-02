package it.axiid.oitc.games;

import it.axiid.oitc.games.states.GameState;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {

    private static boolean joinable;
    private static ArrayList<Player> playingPlayers = new ArrayList<>();
    private static GameState gameState;

    public static void addPlayingPlayer(Player player) {
        playingPlayers.add(player);
    }

    public static ArrayList<Player> getPlayingPlayers() {
        return playingPlayers;
    }

    public static boolean isJoinable() {
        return joinable;
    }

    public static void setJoinable(boolean value) {
        Game.joinable = value;
    }

    public static void setGameState(GameState gameState) {
        Game.gameState = gameState;
    }

    public static GameState getGameState() {
        return gameState;
    }

}
