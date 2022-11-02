package it.axiid.oitc.commands.setjoinspawn;

import it.axiid.oitc.OITC;
import it.axiid.oitc.commands.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetJoinSpawnCommand extends AbstractCommand {

    private final OITC instance;

    public SetJoinSpawnCommand(OITC instance) {
        super("setjoinspawn", "lyzard.setjoinspawn", false);
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        instance.getConfig().createSection("locations.main-spawn");
        instance.getConfig().set("locations.main-spawn", player.getLocation());
        instance.saveConfig();

        player.sendMessage("§aHai impostato correttamente lo spawn di join.");
    }

}
