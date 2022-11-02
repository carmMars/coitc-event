package it.axiid.oitc.games.listeners.events;

import it.axiid.oitc.OITC;
import it.axiid.oitc.games.Game;
import it.axiid.oitc.games.states.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class GamePlayerJoin implements Listener {

    private final OITC instance;

    public GamePlayerJoin(OITC instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(Game.getGameState().equals(GameState.PLAYING)) {
            player.kickPlayer("§cIl gioco è già iniziato!");
            return;
        }

        if (Game.getGameState().equals(GameState.WAITING)) {
            player.sendMessage("§aSei entrato nel gioco!");

            Game.addPlayingPlayer(player);

            for(Player all : Bukkit.getOnlinePlayers()) {
                all.sendMessage("§e" + player.getName() + " §fè entrato nel §cgioco§f! §7(" + Game.getPlayingPlayers().size() + ")");
            }

            if(Game.getPlayingPlayers().size() >= instance.getConfig().getInt("max-players")) {

                Game.setGameState(GameState.STARTING);
                player.sendMessage("§eIl gioco inizierà tra 10 secondi!");

                Bukkit.getScheduler().runTaskLater(instance, () -> {
                    Game.setGameState(GameState.PLAYING);
                    player.sendMessage("§aIl gioco è iniziato!");

                }, 100L);

            }

        }

    }

}
