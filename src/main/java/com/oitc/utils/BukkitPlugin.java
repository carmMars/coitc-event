package com.oitc.utils;

import com.oitc.OITC;

public class BukkitPlugin
{

    private static final OITC instance = OITC.getInstance();

    public static String getVersion() {
        return instance.getDescription().getVersion();
    }

}
