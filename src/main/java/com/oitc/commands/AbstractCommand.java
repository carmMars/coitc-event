package com.oitc.commands;

import com.oitc.OITC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractCommand implements CommandExecutor
{

    private final OITC instance = OITC.getInstance();

    String command;
    String permission;
    boolean canConsoleUse;

    public AbstractCommand(String commandName, String permission, boolean canConsoleUse) {
        this.command = commandName;
        this.permission = permission;
        this.canConsoleUse = canConsoleUse;

        instance.getCommand(commandName).setExecutor(this);
    }

    public abstract void execute(CommandSender sender, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!label.equalsIgnoreCase(this.command)) return true;

        if(!canConsoleUse && !(sender instanceof Player)) {
            sender.sendMessage("Non puoi eseguire questo comando dalla console.");
            return true;
        }

        Player player = (Player) sender;

        if(permission != null && !player.hasPermission(permission)) {
            player.sendMessage("§cNon hai il permesso per eseguire questo comando.");
            return true;
        }

        execute(sender, args);
        return true;
    }
}
