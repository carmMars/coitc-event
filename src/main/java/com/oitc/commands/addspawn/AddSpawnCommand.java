package com.oitc.commands.addspawn;

import com.oitc.OITC;
import com.oitc.commands.AbstractCommand;
import com.oitc.games.Game;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddSpawnCommand extends AbstractCommand {


    public AddSpawnCommand(OITC instance) {
        super("addspawn", "lyzardoitc.addspawn", false);
        this.instance = instance;
    }

    private final OITC instance;

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Game.addSpawnLocation(player);
        player.sendMessage("§aAggiunto uno spawn alla lista");
    }

}
