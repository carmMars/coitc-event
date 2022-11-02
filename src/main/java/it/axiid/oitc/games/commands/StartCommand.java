package it.axiid.oitc.games.commands;

import it.axiid.oitc.commands.AbstractCommand;
import it.axiid.oitc.games.Game;
import it.axiid.oitc.games.states.GameState;
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

        Game.setGameState(GameState.PLAYING);
        player.sendMessage("§aForce start avviato!");

    }

}
