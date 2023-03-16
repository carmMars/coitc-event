package com.oitc.utils;

import com.oitc.games.Game;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LocationsManager {


    public static void saveLocations(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "File Locations creato con successo.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Errore in saveLocationsTxt!");
            }
        }
        try {
            FileWriter writer = new FileWriter(file);
            if (Game.getSpawnLocations() != null) {
                if (Game.getSpawnLocations().size() > 0) {
                    int i = 1;
                    for (Location loc : Game.getSpawnLocations()) {
                        writer.write(loc.getBlockX() + ";" + loc.getBlockY() + ";" + loc.getBlockZ() + ";0;" + loc.getYaw() + System.getProperty("line.separator"));
                        i++;
                    }
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Salvato il file delle locations con " + ChatColor.DARK_GREEN + (i-1) + " entries");
                    writer.close();
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Errore in saveLocations!");
        }
    }




    public static void loadLocations(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                ArrayList<Location> temp = new ArrayList<>();
                while(scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] test = line.replace(line.substring(0, line.indexOf(' ') + 1), "").split(";");
                    temp.add(new Location(
                            Bukkit.getWorld("world"),
                            Double.parseDouble(test[0]),
                            Double.parseDouble(test[1]),
                            Double.parseDouble(test[2]),
                            (float) Double.parseDouble(test[3]),
                            0)

                    );
                }
                Game.setLocations(temp);
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Caricate " + temp.size() + " locations");
            } catch (Exception exception) {
                exception.printStackTrace();
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Errore in loadLocations");
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Il file locations.txt non esiste");
        }
    }

}
