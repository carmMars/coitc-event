package it.axiid.oitc.games.listeners.events;

import it.axiid.oitc.OITC;
import it.axiid.oitc.games.Game;
import it.axiid.oitc.games.states.GameState;
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

            player.setGameMode(GameMode.ADVENTURE);

            Game.addPlayingPlayer(player);
            Bukkit.broadcastMessage("§e" + player.getName() + " §fè entrato nel §cgioco§f! §7(" + Game.getPlayingPlayers().size() + ")");

            if(Game.getPlayingPlayers().size() >= instance.getConfig().getInt("max-players")) {

                Game.setGameState(GameState.STARTING);
                player.sendMessage("§eIl gioco inizierà tra 10 secondi!");

                new BukkitRunnable() {
                    @Override
                    public void run() {

                        if(Game.countdown == 0) {

                            Game.setGameState(GameState.PLAYING);
                            Bukkit.broadcastMessage("§aIl gioco è iniziato.");
                            cancel();

                        }else if(Game.countdown >= 5) {
                            Bukkit.broadcastMessage("§cIl gioco inizierà tra " + Game.countdown);
                        }

                        Game.countdown -= 1;

                    }
                }.runTaskTimer(instance, 20L, 20L);

            }

        }

    }

}
