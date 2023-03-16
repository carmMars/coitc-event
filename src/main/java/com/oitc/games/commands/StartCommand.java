package com.oitc.games.commands;

import com.oitc.games.states.GameState;
import com.oitc.commands.AbstractCommand;
import com.oitc.games.Game;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand extends AbstractCommand {

    public StartCommand() {
        super("start", "lyzard.start", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(Game.getGameState().equals(GameState.PLAYING)) {
            player.sendMessage("§cIl gioco è già iniziato.");
            return;
        }

        if(Game.gameStart()) {
            Game.setGameState(GameState.PLAYING);
            Bukkit.broadcastMessage("§aIl gioco è iniziato.");
        }

    }

}
