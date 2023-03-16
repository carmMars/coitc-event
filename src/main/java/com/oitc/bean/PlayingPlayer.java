package com.oitc.bean;

import org.bukkit.entity.Player;

public class PlayingPlayer {
    private Player player;
    private int kills;
    private long cooldown;


    public PlayingPlayer(Player player, int kills, long cooldown) {
        this.player = player;
        this.kills = kills;
        this.cooldown = cooldown;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setCooldown(long cooldown) {this.cooldown = cooldown;}

    public long getCooldown() {return cooldown;}

}
