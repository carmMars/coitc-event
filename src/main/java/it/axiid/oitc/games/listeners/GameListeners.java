package it.axiid.oitc.games.listeners;

import it.axiid.oitc.OITC;
import it.axiid.oitc.games.listeners.events.GamePlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class GameListeners
{

    private static final OITC instance = OITC.getInstance();

    public static void registerEvents() {
        PluginManager gameListeners = Bukkit.getPluginManager();
        gameListeners.registerEvents(new GamePlayerJoin(instance), instance);
    }

}
