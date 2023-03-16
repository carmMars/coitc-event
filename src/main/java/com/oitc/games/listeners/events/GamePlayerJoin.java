package com.oitc.games.listeners.events;

import com.oitc.OITC;
import com.oitc.bean.PlayingPlayer;
import com.oitc.games.states.GameState;
import com.oitc.games.Game;
import com.oitc.scoreboard.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class GamePlayerJoin implements Listener {

    private final OITC instance;

    public GamePlayerJoin(OITC instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(null);

        if (Game.getGameState().equals(GameState.WAITING)) {


            player.getInventory().clear();
            player.setGameMode(GameMode.ADVENTURE);
            PlayingPlayer playingPlayer = new PlayingPlayer(player, 0, 0);
            Game.addPlayingPlayer(playingPlayer);
            Bukkit.broadcastMessage("§e" + player.getName() + " §fè entrato §7(" + Game.getPlayingPlayers().size() + "/" + instance.getConfig().getInt("max-players") +  ")");
            Scoreboard.scoreboard(player);

            if(Game.getPlayingPlayers().size() == instance.getConfig().getInt("max-players")) {

                Game.setGameState(GameState.STARTING);
                player.sendMessage("§cL'evento tra 10 secondi!");
                Game.countdown = Game._COUNTDOWN;
                new BukkitRunnable() {
                    @Override
                    public void run() {

                        if(Game.countdown == 0) {
                            if(Game.gameStart()) {
                                Game.setGameState(GameState.PLAYING);
                                Bukkit.broadcastMessage("§aIl gioco è iniziato.");
                            }
                            cancel();

                        }else if(Game.countdown <= 5) {
                            Bukkit.broadcastMessage("§cIl gioco inizierà tra " + Game.countdown);
                        }

                        Game.countdown -= 1;

                    }
                }.runTaskTimer(instance, 20L, 20L);

            }

        }

    }

}
