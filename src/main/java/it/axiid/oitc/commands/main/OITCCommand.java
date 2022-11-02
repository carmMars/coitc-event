package it.axiid.oitc.commands.main;

import it.axiid.oitc.OITC;
import it.axiid.oitc.commands.AbstractCommand;
import it.axiid.oitc.utils.BukkitPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OITCCommand extends AbstractCommand {

    private final OITC instance;

    public OITCCommand(OITC instance) {
        super("oitc", "", false);
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(!player.hasPermission("lyzard.command.main")) {
            player.sendMessage("§e• §fRunning §2Tainted§aOITC §fversion §e" + BukkitPlugin.getVersion());
            player.sendMessage("   §8» §fCreated by §eAxiid");
            return;
        }

        if(args.length != 1) {
            player.sendMessage("§c/setspawn §f- Imposta nuovi punti di spawn.");
            player.sendMessage("§c/setjoinspawn §f- Imposta lo spawn iniziale.");
            return;
        }

        if(args[0].equalsIgnoreCase("reload")) {
            instance.reloadConfig();
            instance.saveConfig();

            player.sendMessage("§aHai ricaricato correttamente le configurazioni del plugin.");
            return;
        }

        player.sendMessage("§cArgomento del comando non valido.");

    }

}
