package com.oitc;

import com.oitc.commands.addspawn.AddSpawnCommand;
import com.oitc.games.Game;
import com.oitc.games.commands.StartCommand;
import com.oitc.games.listeners.GameListeners;
import com.oitc.games.states.GameState;
import com.oitc.scoreboard.Scoreboard;
import com.oitc.utils.BungeeCordUtils;
import com.oitc.utils.LocationsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public final class OITC extends JavaPlugin {

    public static OITC instance;

    public static OITC getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        LocationsManager.loadLocations(this.getDataFolder() + File.separator + "locations.txt");
        saveDefaultConfig();

        updateScoreboard();

        registerCommands();
        registerListeners();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeCordUtils());


        Game.setGameState(GameState.WAITING);
    }

    public void registerListeners() {
        // GAMES LISTENER
        GameListeners.registerEvents();
    }

    public void registerCommands() {
        new AddSpawnCommand(this);

        // GAMES COMMANDS
        new StartCommand();
    }

    public void updateScoreboard() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getServer().getOnlinePlayers()) {
                    Scoreboard.updateScoreboard(p);
                }
            }
        }.runTaskTimer(this, 20L, 20L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        LocationsManager.saveLocations(this.getDataFolder() + File.separator + "locations.txt");
        instance = null;
    }

}
