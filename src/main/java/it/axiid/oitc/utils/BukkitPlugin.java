package it.axiid.oitc.utils;

import it.axiid.oitc.OITC;

public class BukkitPlugin
{

    private static final OITC instance = OITC.getInstance();

    public static String getVersion() {
        return instance.getDescription().getVersion();
    }

}
