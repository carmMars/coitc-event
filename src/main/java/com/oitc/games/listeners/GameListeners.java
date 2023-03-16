package com.oitc.games.listeners;

import com.oitc.OITC;
import com.oitc.games.listeners.events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class GameListeners
{

    private static final OITC instance = OITC.getInstance();

    public static void registerEvents() {
        PluginManager gameListeners = Bukkit.getPluginManager();
        gameListeners.registerEvents(new GamePlayerJoin(instance), instance);
        gameListeners.registerEvents(new GamePlayerLogin(instance), instance);
        gameListeners.registerEvents(new GameFoodLevelChange(), instance);
        gameListeners.registerEvents(new GamePlayerQuit(instance), instance);
        gameListeners.registerEvents(new GameArrowOneShot(), instance);
        gameListeners.registerEvents(new GamePlayerDeath(), instance);
        gameListeners.registerEvents(new GamePlayerRespawnEvent(), instance);
        gameListeners.registerEvents(new GameDisableArrowPickup(), instance);
        gameListeners.registerEvents(new GameDropItemEvent(), instance);
    }

}
