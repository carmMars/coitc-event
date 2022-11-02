package it.axiid.oitc;

import it.axiid.oitc.games.commands.StartCommand;
import it.axiid.oitc.games.listeners.GameListeners;
import it.axiid.oitc.commands.main.OITCCommand;
import it.axiid.oitc.commands.setjoinspawn.SetJoinSpawnCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class OITC extends JavaPlugin {

    private static OITC instance;

    public static OITC getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();

        registerCommands();
        registerListeners();
    }

    public void registerListeners() {
        // GAMES LISTENER
        GameListeners.registerEvents();
    }

    public void registerCommands() {
        new OITCCommand(this);
        new SetJoinSpawnCommand(this);

        // GAMES COMMANDS
        new StartCommand();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
    }

}
