package com.oitc.scoreboard;

import com.oitc.OITC;
import com.oitc.games.Game;
import com.oitc.games.states.GameState;
import com.oitc.utils.ScoreHelper;
import com.oitc.utils.TimeUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Scoreboard {
    public static void scoreboard(Player p) {
        ScoreHelper helper = ScoreHelper.createScore(p);
        helper.setTitle("§4§lOITC");
    }


    public static void updateScoreboard(Player p) {
        ScoreHelper helper = ScoreHelper.getByPlayer(p);

        if(ScoreHelper.hasScore(p)) {
            String line = "§7§m-----------------";
            String header = "§cEvento §7(OITC)";
            //WAITING SCOREBOARD
            if(Game.getGameState().equals(GameState.WAITING)) {
                ArrayList<String> list = new ArrayList<>();
                list.add(line);
                list.add(header);
                list.add("§4* §fGiocatori: §7" + Bukkit.getOnlinePlayers().size() + "/" + OITC.instance.getConfig().getInt("max-players"));
                list.add("§4* §fStato: §7In attesa");
                list.add(" ");
                list.add("§e§oplay.lyzard.cc");
                list.add(line);
                helper.setSlotsFromList(list);
            }

            //STARTING SCOREBOARD
            if(Game.getGameState().equals(GameState.STARTING)) {
                ArrayList<String> list = new ArrayList<>();
                list.add(line);
                list.add(header);
                list.add("§4* §fGiocatori: §7" + Bukkit.getOnlinePlayers().size() + "/" + OITC.instance.getConfig().getInt("max-players"));
                list.add("§4* §fStato: §7In attesa");
                list.add(line);
                list.add("§cInizio in: " + TimeUtil.setFormat(Game.countdown));
                list.add(" ");
                list.add("§e§oplay.lyzard.cc");
                list.add(line);
                helper.setSlotsFromList(list);
            }

            //PLAYING SCOREBOARD
            if(Game.getGameState().equals(GameState.PLAYING)) {
                ArrayList<String> list = new ArrayList<>();
                list.add(line);
                list.add(header);
                if(Game.getTopPlayers(0) != null) {
                    list.add("§41§c. §f" + Game.getTopPlayers(0).getPlayer().getName() + ": §7" + Game.getTopPlayers(0).getKills());
                }
                if(Game.getTopPlayers(1) != null) {
                    list.add("§42§c. §f" + Game.getTopPlayers(1).getPlayer().getName() + ": §7" + Game.getTopPlayers(1).getKills());
                }
                if(Game.getTopPlayers(2) != null) {
                    list.add("§43§c. §f" + Game.getTopPlayers(2).getPlayer().getName() + ": §7" + Game.getTopPlayers(2).getKills());
                }
                list.add(" ");
                list.add("§e§oplay.lyzard.cc");
                list.add(line);
                helper.setSlotsFromList(list);
            }

            //ENDING SCOREBOARD
            if(Game.getGameState().equals(GameState.ENDING)) {
                ArrayList<String> list = new ArrayList<>();
                list.add(line);
                list.add(header);
                list.add("§4* §fVincitore: §c" + Game.getTopPlayers(0).getPlayer().getName());
                list.add(" ");
                list.add("§e§oplay.lyzard.cc");
                list.add(line);
                helper.setSlotsFromList(list);
            }
        }

    }



}
