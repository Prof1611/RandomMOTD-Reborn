package com.ThunderKofy;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class RandomMOTD extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().log(Level.INFO, "Thanks for having me as a plugin at your server :)");

        PluginCommand command = getCommand("randommotd");
        if (command != null) {
            command.setExecutor(new Commands(this));
            command.setTabCompleter(new TabCompleter());
        } else {
            getLogger().severe("Command 'randommotd' is missing from plugin.yml");
        }

        getServer().getPluginManager().registerEvents(new InGameMOTD(this), this);
        getServer().getPluginManager().registerEvents(new ServerMOTD(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Goodbye!");
    }
}
