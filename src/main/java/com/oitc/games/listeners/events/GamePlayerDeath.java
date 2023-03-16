package com.oitc.games.listeners.events;

import com.oitc.bean.PlayingPlayer;
import com.oitc.games.Game;
import com.oitc.games.states.GameState;
import com.oitc.utils.BungeeCordUtils;
import com.oitc.utils.PlayingPlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static com.oitc.OITC.instance;


public class GamePlayerDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if(event.getEntity() instanceof Player) {
            if(event.getEntity() != null) {
                //player who died
                Player player = event.getEntity();
                //player who killed
                if(player.getKiller() instanceof Player) {
                    if(player.getKiller() != null) {
                        if(player.getKiller() != player) {
                            PlayingPlayer killer = PlayingPlayerUtils.getPlayer(player.getKiller());
                            player.getKiller().getInventory().addItem(new ItemStack(Material.ARROW));
                            killer.setKills(killer.getKills() + 1);
                            event.setDeathMessage(ChatColor.RED + player.getName() + "§8[" + PlayingPlayerUtils.getPlayer(player).getKills() + "]" + ChatColor.GRAY + " è stato ucciso da " + ChatColor.RED + player.getKiller().getName() + "§8[" + PlayingPlayerUtils.getPlayer(player.getKiller()).getKills() + "]");
                            player.getKiller().setHealth(20);
                            if(killer.getKills() == instance.getConfig().getInt("kills-objective")) {
                                //riproduci l'effetto del lampo
                                player.getWorld().strikeLightningEffect(player.getLocation());
                                BungeeCordUtils.broadcastMessage(player, "§4§lLYZARD §8» §c" + killer.getPlayer().getName() + " §fha appena vinto un §6evento §6OITC§f!");
                                //metti il game state ending
                                Game.setGameState(GameState.ENDING);
                                //imposta la modalita adventure a tutti
                                for(Player p : Bukkit.getOnlinePlayers()) {
                                    p.setGameMode(GameMode.ADVENTURE);
                                }
                                Bukkit.broadcastMessage(ChatColor.RED + "Il server sarà riavviato tra 10 secondi");
                                //fai partire un timer di 10 secondi dopo di che spegni il server
                                Game.countdown = Game._COUNTDOWN;
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        if(Game.countdown == 0) {
                                            Bukkit. dispatchCommand(Bukkit.getConsoleSender(), "stop");
                                            cancel();
                                        } else if (Game.countdown == 7) {
                                            for(Player p : Bukkit.getOnlinePlayers()) {
                                                BungeeCordUtils.connectToServer(p, "lobby");
                                            }
                                        }
                                        Game.countdown -= 1;

                                    }
                                }.runTaskTimer(instance, 20L, 20L);
                            }
                        } else {
                            event.setDeathMessage(null);
                        }
                    } else {
                        event.setDeathMessage(ChatColor.RED + player.getName() + ChatColor.GRAY + " è morto");
                    }
                }
                event.getDrops().removeAll(event.getDrops());
                Bukkit.getScheduler().scheduleSyncDelayedTask(instance, () -> player.spigot().respawn(), 0);

            }
        }
    }
}
